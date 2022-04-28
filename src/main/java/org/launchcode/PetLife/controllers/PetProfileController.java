package org.launchcode.PetLife.controllers;

import org.launchcode.PetLife.models.Pet;
import org.launchcode.PetLife.models.PetMedInfo;
import org.launchcode.PetLife.models.ShotRecord;
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
@RequestMapping("petProfiles")
public class PetProfileController {

    @Autowired
    private PetMedInfoRepository petMedInfoRepository;

    @Autowired
    private PetRepository petRepository;

    @GetMapping
    public String displayPet(Model model) {
        model.addAttribute("title", "All Pets");
        model.addAttribute("pets", petRepository.findAll());
        return "petProfiles/index";
    }
    @GetMapping("create")
    public String displayCreatePetForm(Model model) {
        model.addAttribute("title", "Create Pet");
        model.addAttribute(new Pet());
        return "petProfiles/create";
    }

    @PostMapping("create")
    public String processEditMedInfoForm(@ModelAttribute @Valid Pet newPet, Errors errors, Model model) {

        if (errors.hasErrors()) {
            return "petProfiles/create";
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

        return "petProfiles/detail";
    }


    @GetMapping("petMedInfo/edit")
    public String displayEditMedInfoForm(@RequestParam int petId, Model model) {
        Optional<Pet> result = petRepository.findById(petId);

        if (result.isEmpty()) {
            model.addAttribute("title", "Invalid Pet Id" + petId);
        } else {
            Pet pet = result.get();
            model.addAttribute("title",  "Edit " + pet.getName() + "'s medical information");
            model.addAttribute("pet", pet);
            model.addAttribute(new PetMedInfo());
        }
        return "petProfiles/petMedInfo/edit";
    }

    @PostMapping("petMedInfo/edit")
    public String processEditMedInfoForm(@ModelAttribute @Valid PetMedInfo newPetMedicalInfo, Errors errors, @RequestParam(required = false) int petId, Model model) {

        Optional<Pet> result = petRepository.findById(petId);
        Pet pet = result.get();

        if (errors.hasErrors()) {
            model.addAttribute("title", "You are editing " + pet.getName() + "'s medical information.");
            model.addAttribute("pet", pet);
            return "petProfiles/petMedInfo/edit";
        }

        pet.setPetMedInfo(newPetMedicalInfo);
        petMedInfoRepository.save(newPetMedicalInfo);
        model.addAttribute("pet", pet);

        return "petProfiles/detail";
    }
}
