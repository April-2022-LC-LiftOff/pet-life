package org.launchcode.PetLife.controllers;

import org.launchcode.PetLife.models.*;
import org.launchcode.PetLife.models.data.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
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
    public String displayEditMedInfoForm(@RequestParam Integer petId, Model model, HttpServletRequest request) {
        Optional<Pet> result = petRepository.findById(petId);

        if (result.isEmpty()) {
            model.addAttribute("title", "Invalid Pet Id" + petId);
        } else {
            Pet pet = result.get();
            model.addAttribute("title",  "Edit " + pet.getName() + "'s Medical Information");
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
    public String processEditMedInfoForm(@ModelAttribute @Valid MedInfo newPetMedicalInfo, Errors errors, @RequestParam(required = false) Integer petId, Model model, HttpServletRequest request) {

        Optional<Pet> result = petRepository.findById(petId);
        Pet pet = result.get();

        if (errors.hasErrors()) {
            model.addAttribute("title", "Edit " + pet.getName() + "'s Medical Information.");
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
        Integer oldMedInfoId = 0;

        if (pet.getMedInfo() != null) {
            for (ShotRecord shotRecord : pet.getMedInfo().getShotRecords()) {
                shotRecord.setMedInfo(newPetMedicalInfo);
            }
            for (PastSurgery pastSurgery : pet.getMedInfo().getPastSurgeries()) {
                pastSurgery.setMedInfo(newPetMedicalInfo);
            }
            oldMedInfoId = pet.getMedInfo().getId();
        }


        pet.setMedInfo(newPetMedicalInfo);
        medInfoRepository.save(newPetMedicalInfo);

        if (oldMedInfoId > 0) {
            medInfoRepository.deleteById(oldMedInfoId);
        }

        model.addAttribute("pet", pet);

        return "redirect:../../pet/detail?petId=" + petId;
    }

    @GetMapping("edit/shotRecord")
    public String displayEditShotRecordFrom(@RequestParam(required = false) Integer medInfoId, Model model) {

        Optional<MedInfo> result = medInfoRepository.findById(medInfoId);
        List<ShotRecord> allShotRecords = (List<ShotRecord>) shotRecordRepository.findAll();
        List<ShotRecord> shotRecords = new ArrayList<>();

        for (ShotRecord shotRecord : allShotRecords) {
            if (shotRecord.getMedInfo() == null) {
                shotRecords.add(shotRecord);
            }
        }

        if (result.isEmpty()) {
            model.addAttribute(new ShotRecord());
            model.addAttribute("shotRecords", shotRecords);
        } else {
            MedInfo medInfo = result.get();
            shotRecords.addAll(medInfo.getShotRecords());
            model.addAttribute("shotRecords", shotRecords);
            model.addAttribute(new ShotRecord());

        }

        if (shotRecords.size() > 0) {
            model.addAttribute("title", "All Shot Records");
        } else {
            model.addAttribute("title", "Add New Shot Records");
        }

        return "pet/medInfo/shotRecord";

    }

    @PostMapping("edit/shotRecord")
    public String processEditShotRecordFrom(@ModelAttribute @Valid ShotRecord newShotRecord, Errors errors, Model model, @RequestParam(required = false) Integer[] shotRecordsIds, @RequestParam(required = false) Integer medInfoId) {

        if (errors.hasErrors()) {
            Optional<MedInfo> result = medInfoRepository.findById(medInfoId);
            List<ShotRecord> allShotRecords = (List<ShotRecord>) shotRecordRepository.findAll();
            List<ShotRecord> shotRecords = new ArrayList<>();

            for (ShotRecord shotRecord : allShotRecords) {
                if (shotRecord.getMedInfo() == null) {
                    shotRecords.add(shotRecord);
                }
            }

            if (result.isEmpty()) {
                model.addAttribute("shotRecords", shotRecords);
            } else {
                MedInfo medInfo = result.get();
                shotRecords.addAll(medInfo.getShotRecords());
                model.addAttribute("shotRecords", shotRecords);

            }

            if (shotRecords.size() > 0) {
                model.addAttribute("title", "All Shot Records");
            } else {
                model.addAttribute("title", "Add New Shot Records");
            }

            return "pet/medInfo/shotRecord";
        }

        if (shotRecordsIds != null) {
            for (Integer id : shotRecordsIds) {
                shotRecordRepository.deleteById(id);
            }
        }

        if (newShotRecord.getName() != null) {
            shotRecordRepository.save(newShotRecord);
        }

        return "redirect:shotRecord?medInfoId=" + medInfoId;

    }

    @GetMapping("edit/pastSurgery")
    public String displayEditSurgeryRecordFrom(@RequestParam(required = false) Integer medInfoId, Model model) {

        Optional<MedInfo> result = medInfoRepository.findById(medInfoId);
        List<PastSurgery> allPastSurgeries = (List<PastSurgery>) pastSurgeryRepository.findAll();
        List<PastSurgery> pastSurgeries = new ArrayList<>();
        for (PastSurgery pastSurgery : allPastSurgeries) {
            if (pastSurgery.getMedInfo() == null) {
                pastSurgeries.add(pastSurgery);
            }
        }

        if (result.isEmpty()) {
            model.addAttribute(new PastSurgery());
            model.addAttribute("title", "Add New Surgery Records");
            model.addAttribute("pastSurgeries", pastSurgeries);
        } else {
            MedInfo medInfo = result.get();
            pastSurgeries.addAll(medInfo.getPastSurgeries());
            model.addAttribute("pastSurgeries", pastSurgeries);
            model.addAttribute(new PastSurgery());
        }

        if (pastSurgeries.size() > 0) {
            model.addAttribute("title", "All Surgery Records");
        } else {
            model.addAttribute("title", "Add New Surgery Records");
        }

        return "pet/medInfo/pastSurgery";

    }

    @PostMapping("edit/pastSurgery")
    public String processEditSurgeryRecordFrom(@ModelAttribute @Valid PastSurgery newPastSurgery, Errors errors, Model model, @RequestParam(required = false) Integer[] pastSurgeriesIds, @RequestParam(required = false) Integer medInfoId) {

        if (errors.hasErrors()) {
            Optional<MedInfo> result = medInfoRepository.findById(medInfoId);
            List<PastSurgery> allPastSurgeries = (List<PastSurgery>) pastSurgeryRepository.findAll();
            List<PastSurgery> pastSurgeries = new ArrayList<>();
            for (PastSurgery pastSurgery : allPastSurgeries) {
                if (pastSurgery.getMedInfo() == null) {
                    pastSurgeries.add(pastSurgery);
                }
            }

            if (result.isEmpty()) {
                model.addAttribute("title", "Add New Surgery Records");
                model.addAttribute("pastSurgeries", pastSurgeries);
            } else {
                MedInfo medInfo = result.get();
                pastSurgeries.addAll(medInfo.getPastSurgeries());
                model.addAttribute("pastSurgeries", pastSurgeries);
            }

            if (pastSurgeries.size() > 0) {
                model.addAttribute("title", "All Surgery Records");
            } else {
                model.addAttribute("title", "Add New Surgery Records");
            }

            return "pet/medInfo/pastSurgery";

        }

        if (pastSurgeriesIds != null) {
            for (Integer id : pastSurgeriesIds) {
                pastSurgeryRepository.deleteById(id);
            }
        }

        if (newPastSurgery.getName() != null) {
            pastSurgeryRepository.save(newPastSurgery);
        }

        return "redirect:pastSurgery?medInfoId=" + medInfoId;

    }
}
