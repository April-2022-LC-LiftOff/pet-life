package org.launchcode.PetLife.controllers;

import org.launchcode.PetLife.models.*;
import org.launchcode.PetLife.models.data.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



@Controller
@RequestMapping("pet")
public class PetController {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private MedInfoRepository medInfoRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private ShotRecordRepository shotRecordRepository;

    @Autowired
    private PastSurgeryRepository pastSurgeryRepository;

    private void deleteUnrelatedShotSurgery() {
        List<ShotRecord> allShotRecords = (List<ShotRecord>) shotRecordRepository.findAll();
        for (ShotRecord shotRecord : allShotRecords) {
            if (shotRecord.getMedInfo() == null) {
                shotRecordRepository.delete(shotRecord);
            }
        }
        List<PastSurgery> allPastSurgeries = (List<PastSurgery>) pastSurgeryRepository.findAll();
        for (PastSurgery pastSurgery : allPastSurgeries) {
            if (pastSurgery.getMedInfo() == null) {
                pastSurgeryRepository.delete(pastSurgery);
            }
        }
    }

    @GetMapping
    public String displayAllPets(Model model, HttpServletRequest request) {

        this.deleteUnrelatedShotSurgery();

        List<Pet> pets;
        int role = AppController.currentLoginInfo(request);
        if (role == 1) {
            User currentUser = AppController.getCurrentUser(userRepository, request);
            pets = currentUser.getPets();
            if (pets != null) {
                for (Pet pet : pets) {
                    pet.updateAge();
                }
            }
        } else {
            pets = (List<Pet>) petRepository.findAll();
//            allPets.stream().forEach(pet-> System.out.println(pet));
            model.addAttribute("pets", pets);

        }
        model.addAttribute("pets", pets);
        model.addAttribute("title", "All Pets");
        model.addAttribute("role", role);
        return "pet/index";

    }

    @GetMapping("create")
    public String displayCreatePetProfileForm(Model model,HttpServletRequest request) {

        this.deleteUnrelatedShotSurgery();

        model.addAttribute("title", "Create a Pet Profile");
        model.addAttribute(new Pet());
        model.addAttribute("role", AppController.currentLoginInfo(request));
        return "pet/create";

    }

    @PostMapping("create")
    public String processCreatePetProfileForm(@ModelAttribute @Valid Pet newPet, Errors errors, Model model, HttpServletRequest request) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Create a Pet Profile");
            model.addAttribute("role", AppController.currentLoginInfo(request));
            return "pet/create";
        }


        if (newPet.getBDate() != null) {
            newPet.setAgeYear(null);
            newPet.setAgeMonth(null);
        }

        User currentUser = AppController.getCurrentUser(userRepository, request);
        newPet.setUser(currentUser);

        petRepository.save(newPet);

        return "redirect:";
    }


    @GetMapping("delete")
    public String displayDeletePetProfileForm(Model model, HttpServletRequest request) {

        this.deleteUnrelatedShotSurgery();

        User currentUser = AppController.getCurrentUser(userRepository, request);

        model.addAttribute("title", "Delete Pet Profiles");
        model.addAttribute("pets", currentUser.getPets());
        model.addAttribute("role", AppController.currentLoginInfo(request));

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
    public String displayPetProfileDetails(@RequestParam Integer petId, Model model, HttpServletRequest request) {

        Optional<Pet> result = petRepository.findById(petId);

        if (result.isEmpty()) {
            model.addAttribute("title", "Invalid Pet Id" + petId);
        } else {
            Pet pet = result.get();
            model.addAttribute("title", pet.getName() + "'s information" );
            model.addAttribute("pet", pet);
        }

        model.addAttribute("role", AppController.currentLoginInfo(request));

        return "pet/detail";
    }



}

