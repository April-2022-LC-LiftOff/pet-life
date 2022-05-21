package org.launchcode.PetLife.controllers;

import org.launchcode.PetLife.models.MedInfo;
import org.launchcode.PetLife.models.PastSurgery;
import org.launchcode.PetLife.models.Pet;
import org.launchcode.PetLife.models.ShotRecord;
import org.launchcode.PetLife.models.data.MedInfoRepository;
import org.launchcode.PetLife.models.data.PastSurgeryRepository;
import org.launchcode.PetLife.models.data.PetRepository;
import org.launchcode.PetLife.models.data.ShotRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("pet/medInfo")
public class PetMedInfoController {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private MedInfoRepository medInfoRepository;

    @Autowired
    private ShotRecordRepository shotRecordRepository;

    @Autowired
    private PastSurgeryRepository pastSurgeryRepository;


    @GetMapping("edit")
    public String displayEditMedInfoForm(@RequestParam int petId, Model model, HttpServletRequest request) {
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
        model.addAttribute("role", AppController.currentLoginInfo(request));
        return "pet/medInfo/edit";
    }

    @PostMapping("edit")

    public String processEditMedInfoForm(@ModelAttribute @Valid MedInfo newPetMedicalInfo, Errors errors, @RequestParam(required = false) int petId, Model model, HttpServletRequest request) {

        Optional<Pet> result = petRepository.findById(petId);
        Pet pet = result.get();

        if (errors.hasErrors()) {
            model.addAttribute("title", "Edit " + pet.getName() + "'s medical information.");
            model.addAttribute("pet", pet);
            model.addAttribute("role", AppController.currentLoginInfo(request));
            return "pet/medInfo/edit";
        }

        List<ShotRecord> allShotRecords = (List<ShotRecord>) shotRecordRepository.findAll();
        List<PastSurgery> allPastSurgeries = (List<PastSurgery>) pastSurgeryRepository.findAll();

        for (ShotRecord shotRecord : allShotRecords) {
            if (shotRecord.getMedInfo() == null) {
                shotRecord.setMedInfo(newPetMedicalInfo);
            }
        }

        for (PastSurgery pastSurgery : allPastSurgeries) {
            if (pastSurgery.getMedInfo() == null) {
                pastSurgery.setMedInfo(newPetMedicalInfo);
            }
        }

        if (pet.getMedInfo() != null) {
            for (ShotRecord shotRecord : pet.getMedInfo().getShotRecords()) {
                shotRecord.setMedInfo(newPetMedicalInfo);
            }
            for (PastSurgery pastSurgery : pet.getMedInfo().getPastSurgeries()) {
                pastSurgery.setMedInfo(newPetMedicalInfo);
            }
        }

        pet.setMedInfo(newPetMedicalInfo);
        medInfoRepository.save(newPetMedicalInfo);

        model.addAttribute("pet", pet);

        return "redirect:../../pet/detail?petId=" + petId;
    }

    @GetMapping("edit/shotRecord")
    public String displayEditShotRecordFrom(@RequestParam(required = false) int medInfoId, Model model) {

        Optional<MedInfo> result = medInfoRepository.findById(medInfoId);

        if (result.isEmpty()) {
            model.addAttribute("title", "Invalid MedInfo Id" + medInfoId);
            model.addAttribute(new ShotRecord());
            model.addAttribute("title", "Add New Shot Records");
        } else {
            MedInfo medInfo = result.get();
            List<ShotRecord> shotRecords = medInfo.getShotRecords();

            List<ShotRecord> allShotRecords = (List<ShotRecord>) shotRecordRepository.findAll();

            for (ShotRecord shotRecord : allShotRecords) {
                if (shotRecord.getMedInfo() == null) {
                  shotRecords.add(shotRecord);
                }
            }

            model.addAttribute("shotRecords", shotRecords);
            model.addAttribute(new ShotRecord());
            model.addAttribute("title", "All Shot Records");
        }

        return "pet/medInfo/shotRecord";

    }

    @PostMapping("edit/shotRecord")
    public String processEditShotRecordFrom(@ModelAttribute @Valid ShotRecord newShotRecord, Errors errors, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Edit Shot Record");
            model.addAttribute(new ShotRecord());
            return  "pet/medInfo/shotRecord";
        }

        shotRecordRepository.save(newShotRecord);
        return "pet/medInfo/closeWindow";

    }

    @GetMapping("edit/pastSurgery")
    public String displayEditSurgeryRecordFrom(@RequestParam(required = false) int medInfoId, Model model) {

        Optional<MedInfo> result = medInfoRepository.findById(medInfoId);

        if (result.isEmpty()) {
            model.addAttribute("title", "Invalid MedInfo Id" + medInfoId);
            model.addAttribute(new PastSurgery());
            model.addAttribute("title", "Add New Surgery Records");
        } else {
            MedInfo medInfo = result.get();
            model.addAttribute("pastSurgeries", medInfo.getPastSurgeries());
            model.addAttribute(new PastSurgery());
            model.addAttribute("title", "All Surgery Records");
        }

        return "pet/medInfo/pastSurgery";

    }

    @PostMapping("edit/pastSurgery")
    public String processEditSurgeryRecordFrom(@ModelAttribute @Valid PastSurgery newPastSurgery, Errors errors, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Edit Surgery Record");
            model.addAttribute(new PastSurgery());
            return  "pet/medInfo/pastSurgery";
        }

        pastSurgeryRepository.save(newPastSurgery);
        return "pet/medInfo/closeWindow";

    }
}
