package org.launchcode.PetLife.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("")
public class HomeController {

    @GetMapping
    public String displayHomePage(Model model, HttpServletRequest request) {
        model.addAttribute("title", "What is PetLife?");
        model.addAttribute("role", AppController.currentLoginInfo(request));
        return "index";
    }
}
