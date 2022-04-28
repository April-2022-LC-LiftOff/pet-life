package org.launchcode.PetLife.controllers;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping
public class HomeController {

    @GetMapping
    public String displayHomePage(Model model) {
        return "index";

    }
}
