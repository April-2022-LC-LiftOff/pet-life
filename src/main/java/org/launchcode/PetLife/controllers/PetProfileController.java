package org.launchcode.PetLife.controllers;

import org.launchcode.PetLife.models.Pet;
import org.launchcode.PetLife.models.MedInfo;
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
@RequestMapping("pet")
public class PetProfileController {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private PetMedInfoRepository petMedInfoRepository;

    @GetMapping
    public String displayPet(Model model) {
        model.addAttribute("title", "All Pets");
        model.addAttribute("pets", petRepository.findAll());
        return "pet/index";
    }

    @GetMapping("create")
    public String displayCreatePetForm(Model model) {
        model.addAttribute("title", "Create Pet Profile");
        model.addAttribute(new Pet());
        return "pet/create";
    }

    @PostMapping("create")
    public String processCreatePetForm(@ModelAttribute @Valid Pet newPet, Errors errors, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Create Pet Profile");
            return "pet/create";
        }

        petRepository.save(newPet);
        return "redirect:";
    }

    @GetMapping("detail")
    public String displayPetDetails(@RequestParam Integer petId, Model model) {


        Optional<Pet> result = petRepository.findById(petId);

        if (result.isEmpty()) {
            model.addAttribute("title", "Invalid Pet Id" + petId);
        } else {
            Pet pet = result.get();
            model.addAttribute("title", pet.getName() + "'s information" );
            model.addAttribute("pet", pet);
        }

        return "pet/detail";
    }


    @GetMapping("medInfo/edit")
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

    @PostMapping("medInfo/edit")
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
