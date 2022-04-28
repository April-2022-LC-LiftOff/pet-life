package org.launchcode.PetLife.controllers;

import org.launchcode.PetLife.models.PetBio;
import org.launchcode.PetLife.models.data.PetBioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "petbio")
public class PetBioController {


    @Autowired
    private PetBioRepository petBioRepository;



    @GetMapping
    public String processPetBioInfo(Model model) {
        model.addAttribute("title", "Pet Biography");
        model.addAttribute("pets", petBioRepository.findAll());

        return "petbio/index";
    }

    @GetMapping("add")
    public String displayAddPetBioForm(Model model) {
        model.addAttribute("title", "Add Pet Biography");
        model.addAttribute(new PetBio());
        return "petbio/add";
    }

    @PostMapping("add")
    public String processPetBioInfo(@ModelAttribute @Valid PetBio newPetBio, Errors errors, Model model){
        if(errors.hasErrors()){
            model.addAttribute("title", "Add Pet Biography");
            return "petbio/add";
        }
        petBioRepository.save(newPetBio);
        return "redirect:";
    }


    @GetMapping("delete")
    public String displaydeletePetBioForm(Model model) {
        model.addAttribute("title", "Delete Pet Profile");
        model.addAttribute("petz", petBioRepository.findAll());
        return "petbio/delete";
    }

    @PostMapping("delete")
    public String processDeletePetBioInfoForm(@RequestParam(required = false) int[] petIds) {

        if (petIds != null) {
            for (int id : petIds) {
                petBioRepository.deleteById(id);
            }
        }

        return "redirect:";
    }

}
