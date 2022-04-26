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
        return "index";
    }

    @GetMapping("add")
    public String displayAddPetBioForm(Model model) {
        model.addAttribute("title", "Add Pet Biography");
//        model.addAttribute(new PetBio());
        return "petbio/add";
    }

    @PostMapping("add")
    public String processPetBioInfo(@RequestParam String petName, @RequestParam Integer petAge){
        PetBioData.add(new PetBio(petName, petAge));

        return "petbio/index";
    }

    @GetMapping("delete")
    public String displayDeletePetBioInfo(Model model){
        model.addAttribute("title", "Delete Pet Biography" );
        model.addAttribute("pets", PetBioData.getAll());
        return "petbio/delete";
    }

    @PostMapping("delete")
    public String processDeletePetBioInfo(@RequestParam int[] petids){
        for (int id: petids){
           PetBioData.remove(id);
        }
        return"petbio/index";

    }


}
