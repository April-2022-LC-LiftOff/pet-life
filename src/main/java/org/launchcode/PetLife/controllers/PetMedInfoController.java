package org.launchcode.PetLife.controllers;

import org.launchcode.PetLife.models.MedInfo;
import org.launchcode.PetLife.models.Pet;
import org.launchcode.PetLife.models.data.PetMedInfoRepository;
import org.launchcode.PetLife.models.data.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("pet/medInfo")
public class PetMedInfoController {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private PetMedInfoRepository petMedInfoRepository;


    @GetMapping("edit")
    public String displayEditMedInfoForm(@RequestParam int petId, Model model) {
        Optional<Pet> result = petRepository.findById(petId);

        if (result.isEmpty()) {
            model.addAttribute("title", "Invalid Pet Id" + petId);
        } else {
            Pet pet = result.get();
            model.addAttribute("title",  "Edit " + pet.getName() + "'s medical information");
            model.addAttribute("pet", pet);
            if (pet.getMedInfo() != null) {
                model.addAttribute("medInfo", pet.getMedInfo());
            } else {
                model.addAttribute(new MedInfo());
            }

        }
        return "pet/medInfo/edit";
    }

    @PostMapping("edit")
    public String processEditMedInfoForm(@ModelAttribute @Valid MedInfo newPetMedicalInfo, Errors errors, @RequestParam(required = false) int petId, Model model) {

        Optional<Pet> result = petRepository.findById(petId);
        Pet pet = result.get();

        if (errors.hasErrors()) {
            model.addAttribute("title", "Edit " + pet.getName() + "'s medical information.");
            model.addAttribute("pet", pet);
            return "pet/medInfo/edit";
        }

        pet.setMedInfo(newPetMedicalInfo);
        petMedInfoRepository.save(newPetMedicalInfo);
        model.addAttribute("pet", pet);

        return "pet/detail";
    }
}
