package org.launchcode.PetLife.controllers;

import org.launchcode.PetLife.models.*;
import org.launchcode.PetLife.models.data.PetMedInfoRepository;
import org.launchcode.PetLife.models.data.PetRepository;
import org.launchcode.PetLife.models.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.Collection;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("pet")
public class PetController {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private PetMedInfoRepository petMedInfoRepository;

    @Autowired
    private UserRepository userRepository;

    public User getCurrentUser(HttpServletRequest request) {
        Principal userPrincipal = request.getUserPrincipal();
        String userEmail = userPrincipal.getName();
        return userRepository.findByEmail(userEmail);
    }


    @GetMapping
    public String displayAllPets(Model model, HttpServletRequest request) {
        User currentUser = getCurrentUser(request);

        model.addAttribute("title", "All Pets");

        List<Pet> allPets = (List<Pet>) petRepository.findAll();
        List<Pet> myPets = currentUser.myPets(allPets);

        model.addAttribute("pets", myPets);

        return "pet/index";
    }

    @GetMapping("create")
    public String displayCreatePetProfileForm(Model model) {
        model.addAttribute("title", "Create a Pet Profile");
        model.addAttribute(new Pet());
        return "pet/create";
    }

    @PostMapping("create")
    public String processCreatePetProfileForm(@ModelAttribute @Valid Pet newPet, Errors errors, Model model, HttpServletRequest request) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Create a Pet Profile");
            return "pet/create";
        }

        User currentUser = getCurrentUser(request);
        newPet.setUser(currentUser);

        petRepository.save(newPet);
        return "redirect:";
    }


    @GetMapping("delete")
    public String displayDeletePetProfileForm(Model model, HttpServletRequest request) {
        User currentUser = getCurrentUser(request);

        model.addAttribute("title", "Delete Pet Profiles");

        List<Pet> allPets = (List<Pet>) petRepository.findAll();
        List<Pet> myPets = currentUser.myPets(allPets);
        model.addAttribute("pets", myPets);

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
