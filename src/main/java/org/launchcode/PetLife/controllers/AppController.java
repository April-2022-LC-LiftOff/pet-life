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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;

@Controller
//@RequestMapping("login")
public class AppController {
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("/login")
    public String viewHomePage() {
        return "login";
    }

//    @ResponseBody
//    @GetMapping("/owner")
//    public String processOwnerProfile(User user, Model model) {
//        model.addAttribute("users", user);
//
//        userRepo.save(user);
//
//        return "/owner";
//    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());

        return "register";

    }

    @PostMapping("/register")
    public String processRegister(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        Role userRole = roleRepository.findByName("ROLE_USER");
        user.setRoles(Arrays.asList(userRole));
        user.setEnabled(true);
      userRepo.save(user);

        return "login";
    }




}
