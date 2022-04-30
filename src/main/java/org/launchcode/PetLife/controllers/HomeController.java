package org.launchcode.PetLife.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("")
public class HomeController {

    @GetMapping
    public String displayHomePage(Model model) {
        model.addAttribute("title", "What is PetLife?");
        return "index";
    }
}
