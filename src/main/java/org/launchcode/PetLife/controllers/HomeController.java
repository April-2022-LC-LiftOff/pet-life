package org.launchcode.PetLife.controllers;


import org.launchcode.PetLife.models.*;
import org.launchcode.PetLife.models.data.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("")
public class HomeController {

    @Autowired
    private ShotRecordRepository shotRecordRepository;

    @Autowired
    private PastSurgeryRepository pastSurgeryRepository;

    @Autowired
    private UserRepository userRepository;



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

    @GetMapping("vets")
    public String displayVetProfilesPage(Model model, HttpServletRequest request) {
        List<User> allUsers = userRepository.findAll();
        List<User> vets = new ArrayList<>();
        for (User user : allUsers) {
            if (user.getRoles().iterator().next().getName().equals("ROLE_ADMIN")) {
                vets.add(user);
            }
        }
        model.addAttribute("vets", vets);
        model.addAttribute("title", "All Vets");
        model.addAttribute("role", AppController.currentLoginInfo(request));

        return "vets";
    }
}
