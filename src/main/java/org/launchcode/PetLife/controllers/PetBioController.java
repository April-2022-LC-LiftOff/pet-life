package org.launchcode.PetLife.controllers;

import org.launchcode.PetLife.models.PetBio;
import org.launchcode.PetLife.models.data.PetBioData;
import org.launchcode.PetLife.models.data.PetBioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "petbio")
public class PetBioController {


//    @Autowired
//    private PetBioRepository petBioRepository;



    @GetMapping("")
    public String processPetBioInfo(Model model) {
        model.addAttribute("title", "Pet Biography");
//        model.addAttribute("pets", petBioRepository.findAll());
      model.addAttribute("pets", PetBioData.getAll());
        return "petbio/index";
    }

    @GetMapping("add")
    public String displayAddPetBioForm(Model model) {
        model.addAttribute("title", "Add Pet Biography");
//        model.addAttribute(new PetBio());
        return "petbio/add";
    }

    @PostMapping("add")
    public String processPetBioInfo(@ModelAttribute @Valid PetBio newPetBio, Errors errors, Model model){

        if(errors.hasErrors()){
            model.addAttribute("title", "Add Pet Biography");
            model.addAttribute("errorMsg", "Bad Data");
            return "petbio/add";
        }

        PetBioData.add(newPetBio);
        return "petbio/index";
    }


    @GetMapping("delete")
    public String displaydeletePetBioForm(Model model) {
        model.addAttribute("title", "Delete Pet Profile");
        model.addAttribute("events", PetBioData.getAll());
        return "petbio/delete";
    }

    @PostMapping("delete")
    public String processDeletePetBioInfoForm(@RequestParam(required = false) int[] petIds) {

        if (petIds != null) {
            for (int id : petIds) {
                PetBioData.remove(id);
            }
        }

        return "petbio/delete";
    }

}
