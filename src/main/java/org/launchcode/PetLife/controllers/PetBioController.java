package org.launchcode.PetLife.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "pet")
public class PetBioController {

    @GetMapping("")
    @ResponseBody
   public String processPetBioInfo() {

       return "pet";
   }
}
