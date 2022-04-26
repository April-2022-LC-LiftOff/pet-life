package org.launchcode.PetLife.controllers;

import org.launchcode.PetLife.models.PetBio;
import org.launchcode.PetLife.models.data.PetBioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping(value = "petbio")
public class PetBioController {


    @Autowired
    private PetBioRepository petBioRepository;


    @GetMapping("")
//    @ResponseBody
    public String processPetBioInfo(Model model) {
        model.addAttribute("title", "Pet Biography");
        model.addAttribute("petBiographys", petBioRepository.findAll());
        return "petbio/index";
    }

    @GetMapping("add")
    public String displayAddPetBioForm(Model model) {
        model.addAttribute("title", "Add Pet Biography");
        model.addAttribute(new PetBio());
        return "petbio/add";
    }

    @PostMapping("add")
    public String processAddPedBioForm(@ModelAttribute @Valid PetBio newPetBio,
                                       Errors errors, Model model) {
        petBioRepository.save(newPetBio);
        if (errors.hasErrors()) {
            model.addAttribute(new PetBio());
            System.out.println(newPetBio);
            return "petbio/add";
        }
        petBioRepository.save(newPetBio);

        return "redirect:";
    }

    @GetMapping("view")
    public String displayViewPetBio(Model model, @PathVariable int petbioId ) {



            Optional optPetBio = petBioRepository.findById(petbioId);
            if (optPetBio.isPresent()) {
                PetBio petBio = (PetBio) optPetBio.get();
                model.addAttribute("petbio", petBio);
                return "petbio/view";
            }
        return "petbio/view";
    }
}
