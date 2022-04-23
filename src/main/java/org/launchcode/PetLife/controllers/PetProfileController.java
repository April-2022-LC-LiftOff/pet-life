package org.launchcode.PetLife.controllers;

import org.launchcode.PetLife.models.PetMedInfo;
import org.launchcode.PetLife.models.data.PetMedInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("petProfiles")
public class PetProfileController {

    @Autowired
    private PetMedInfoRepository petMedInfoRepository;

    @GetMapping("medInfo")
    public String displayMedInfo(Model model) {
        return "petProfiles/medInfo/index";
    }

    @GetMapping("medInfo/edit")
    public String displayEditMedInfoForm(Model model) {
        model.addAttribute(new PetMedInfo());
        return "petProfiles/medInfo/edit";
    }

    @PostMapping("medInfo/edit")
    public String processEditMedInfoForm(@ModelAttribute @Valid PetMedInfo newPetMedicalInfo, Errors errors, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute(new PetMedInfo());
            return "petProfiles/medInfo/edit";
        }
        petMedInfoRepository.save(newPetMedicalInfo);
        return "redirect";
    }

}
