package org.launchcode.PetLife.controllers;


import net.bytebuddy.utility.RandomString;
import org.launchcode.PetLife.models.CustomerNotFoundException;
import org.launchcode.PetLife.models.User;
import org.launchcode.PetLife.models.UserServices;

import org.launchcode.PetLife.models.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

@Controller
public class ForgotPasswordController {
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private UserServices userService;

    @GetMapping("/forgot_password")
    public String showForgotPasswordForm(Model model, HttpServletRequest request) {
//        model.addAttribute("title", "Forgot Password");
        int role = AppController.currentLoginInfo(request);
        model.addAttribute("role",role);
        return "forgot_password";
    }

    @PostMapping("/forgot_password")

    public String processForgotPassword(HttpServletRequest request, Model model) {
        String email = request.getParameter("email");
        String token = RandomString.make(30);

        try {
            userService.updateResetPasswordToken(token, email);
            String resetPasswordLink = Utility.getSiteURL(request) + "/reset_password?token=" + token;
            sendEmail(email, resetPasswordLink);
            model.addAttribute("message", "We have sent a reset password link to your email. Please check.");

        } catch (CustomerNotFoundException ex) {
            model.addAttribute("error", "We could not find any account with this email.");

        } catch (UnsupportedEncodingException | MessagingException e) {
            model.addAttribute("error", "Error while sending email");
        }

        return "forgot_password";
    }

    public void sendEmail(String recipientEmail, String link)
            throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("contact@petlife.com", "PetLife Support");
        helper.setTo(recipientEmail);

        String subject = "Here's the link to reset your password";

        String content = "<p>Hello,</p>"
                + "<p>You have requested to reset your password.</p>"
                + "<p>Click the link below to change your password:</p>"
                + "<p><a href=\"" + link + "\">Change my password</a></p>"
                + "<br>"
                + "<p>Ignore this email if you do remember your password, "
                + "or you have not made the request.</p>";

        helper.setSubject(subject);

        helper.setText(content, true);

        mailSender.send(message);
    }

    @GetMapping("/reset_password")
    public String showResetPasswordForm(@Param(value = "token") String token, Model model, HttpServletRequest request) {
        User customer = userService.getByResetPasswordToken(token);
        model.addAttribute("token", token);

        if (customer == null) {
            model.addAttribute("message", "Invalid Token");
            return "message";
        }

        int role = AppController.currentLoginInfo(request);
        model.addAttribute("role",role);

        return "reset_password";
    }
        @PostMapping("/reset_password")
        public String processResetPassword(HttpServletRequest request, Model model) {
            String token = request.getParameter("token");
            String password = request.getParameter("password");

            User customer = userService.getByResetPasswordToken(token);


            if (customer == null) {
                model.addAttribute("message", "Invalid password");
                return "message";
            } else {
                userService.updatePassword(customer, password);

                model.addAttribute("message", "You have successfully changed your password.");
            }

            return "message";
        }
}