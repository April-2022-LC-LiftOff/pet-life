package org.launchcode.PetLife.controllers;


import org.launchcode.PetLife.models.PastSurgery;
import org.launchcode.PetLife.models.ShotRecord;
import org.launchcode.PetLife.models.data.PastSurgeryRepository;
import org.launchcode.PetLife.models.data.ShotRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("")
public class HomeController {

    @Autowired
    private ShotRecordRepository shotRecordRepository;

    @Autowired
    private PastSurgeryRepository pastSurgeryRepository;

    @GetMapping
    public String displayHomePage(Model model, HttpServletRequest request) {

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

        model.addAttribute("title", "What is PetLife?");
        model.addAttribute("role", AppController.currentLoginInfo(request));
        return "index";
    }
}
