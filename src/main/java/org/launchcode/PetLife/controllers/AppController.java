package org.launchcode.PetLife.controllers;

import org.launchcode.PetLife.models.Role;
import org.launchcode.PetLife.models.User;
import org.launchcode.PetLife.models.data.RoleRepository;
import org.launchcode.PetLife.models.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.Arrays;
import java.util.List;

@Controller
//@RequestMapping("login")
public class AppController {
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RoleRepository roleRepository;

    public static User getCurrentUser(UserRepository userRepository, HttpServletRequest request) {
        Principal userPrincipal = request.getUserPrincipal();
        String userEmail = userPrincipal.getName();
        return userRepository.findByEmail(userEmail);
    }

    public static int currentLoginInfo(HttpServletRequest request) {
        if (request.getUserPrincipal() == null) {
            return 0;
        }
        if (request.isUserInRole("ROLE_USER")) {
            return 1;
        } else {
            return 2;
        }
    }

    @GetMapping("/login")
    public String viewHomePage(Model model, HttpServletRequest request) {
        model.addAttribute("role", AppController.currentLoginInfo(request));
        return "login";
    }


    @GetMapping("/register")
    public String showRegistrationForm(Model model, HttpServletRequest request) {
        model.addAttribute("user", new User());
        model.addAttribute("role", AppController.currentLoginInfo(request));
        return "register";

    }

    @PostMapping("/register")
    public String processRegister(@ModelAttribute @Valid User user, Model model, Error errors) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        if (userRepo.existsByEmail(user.getEmail())) {

            model.addAttribute("error", "This email already exist with another account. ");

                return "/register";
            }



        Role userRole = null;

        if (null == user.getIsAdmin()) {
            userRole = roleRepository.findByName("ROLE_USER");
        } else {
            userRole = roleRepository.findByName("ROLE_ADMIN");
        }
        user.setRoles(Arrays.asList(userRole));
        user.setEnabled(true);
        userRepo.save(user);
        return "login";
    }
}




