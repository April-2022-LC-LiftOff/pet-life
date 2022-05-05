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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("pet")
public class PetController {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private PetMedInfoRepository petMedInfoRepository;

    @GetMapping
    public String displayAllPets(Model model) {
        ArrayList<Pet> allPets = (ArrayList<Pet>)petRepository.findAll();
        for (Pet pet : allPets) {
            pet.updateAge();
        }
        model.addAttribute("title", "All Pets");
        model.addAttribute("pets", petRepository.findAll());
        return "pet/index";
    }

    @GetMapping("create")
    public String displayCreatePetProfileForm(Model model) {
        model.addAttribute("title", "Create a Pet Profile");
        model.addAttribute(new Pet());
        return "pet/create";
    }

    @PostMapping("create")
    public String processCreatePetProfileForm(@ModelAttribute @Valid Pet newPet, Errors errors, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Create a Pet Profile");
            return "pet/create";
        }

        if (newPet.getBDate() != null) {
            newPet.setAgeYear(null);
            newPet.setAgeMonth(null);
        }

        petRepository.save(newPet);
        return "redirect:";
    }


    @GetMapping("delete")
    public String displayDeletePetProfileForm(Model model) {
        model.addAttribute("title", "Delete Pet Profiles");
        model.addAttribute("pets", petRepository.findAll());
        return "pet/delete";
    }

    @PostMapping("delete")
    public String processDeletePetProfileForm(@RequestParam(required = false) int[] petIds) {
        if (petIds != null) {
            for (int id : petIds) {
                petRepository.deleteById(id);
            }
        }

        return "redirect:delete";
    }

    @GetMapping("detail")
    public String displayPetProfileDetails(@RequestParam Integer petId, Model model) {

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

}
