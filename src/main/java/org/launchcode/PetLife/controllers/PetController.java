package org.launchcode.PetLife.controllers;

import org.launchcode.PetLife.models.*;
import org.launchcode.PetLife.models.data.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
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
        List<Pet> ownedPets = currentUser.getPets();

        if (ownedPets != null) {
            for (Pet pet : ownedPets) {
                pet.updateAge();
            }
        }

      
        model.addAttribute("title", "All Pets");
        model.addAttribute("pets", ownedPets);

        return "pet/index";
    }
@PreAuthorize("hasRole('ROLE_USER')")
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


        if (newPet.getBDate() != null) {
            newPet.setAgeYear(null);
            newPet.setAgeMonth(null);
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
        model.addAttribute("pets", currentUser.getPets());

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
