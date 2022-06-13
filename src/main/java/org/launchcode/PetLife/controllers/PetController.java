package org.launchcode.PetLife.controllers;

import org.launchcode.PetLife.models.*;
import org.launchcode.PetLife.models.data.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("pet")
public class PetController {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private ShotRecordRepository shotRecordRepository;

    @Autowired
    private PastSurgeryRepository pastSurgeryRepository;


    @GetMapping
    public String displayAllPets(Model model, HttpServletRequest request) {
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
            model.addAttribute("pets", pets);

        }
        model.addAttribute("pets", pets);
        model.addAttribute("title", "All Pets");
        model.addAttribute("role", role);
        return "pet/index";

    }

    @GetMapping("create")
    public String displayCreatePetProfileForm(Model model, HttpServletRequest request, @RequestParam(required = false) Integer petId) {

        if (petId != null) {
            Optional<Pet> result = petRepository.findById(petId);
            Pet pet = result.get();
            model.addAttribute("pet", pet);
        } else {
            model.addAttribute(new Pet());
        }

        model.addAttribute("title", "Create/Edit a Pet Profile");
        model.addAttribute("role", AppController.currentLoginInfo(request));

        return "pet/create";

    }

    @PostMapping("create")
    public String processCreatePetProfileForm(@ModelAttribute @Valid Pet newPet, Errors errors, Model model, HttpServletRequest request, @RequestParam(value = "image", required = false) MultipartFile multipartFile, @RequestParam(required=false) Integer petId, @RequestParam(required=false) boolean deletePhoto) throws IOException {

        if (errors.hasErrors()) {

            model.addAttribute("title", "Create/Edit a Pet Profile");
            model.addAttribute("role", AppController.currentLoginInfo(request));

            return "pet/create";
        }


        if (newPet.getBDate() != null) {
            newPet.setAgeYear(null);
            newPet.setAgeMonth(null);
        }

        User currentUser = AppController.getCurrentUser(userRepository, request);
        newPet.setUser(currentUser);
        Integer actualPetId = 0;

        if (multipartFile.getBytes().length == 0){
            if (petId != null) {
                Optional<Pet> result = petRepository.findById(petId);
                Pet pet = result.get();
                if (deletePhoto) {
                    File file = new File("src/main/resources/static" + pet.getPhotosImagePath());
                    file.delete();
                    pet.setPhotosImagePath(null);
                }

                pet.updatePet(newPet);
                petRepository.save(pet);
                actualPetId = petId;
            } else {
                actualPetId = petRepository.save(newPet).getId();

            }
        } else {
            byte[] byteObjects = new byte[multipartFile.getBytes().length];
            int i = 0;
            for (byte b : multipartFile.getBytes()) {
                byteObjects[i++] = b;
            }
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            newPet.setPhotos(byteObjects);
            String uploadDir = "";
            if (petId != null) {
                Optional<Pet> result = petRepository.findById(petId);
                Pet pet = result.get();
                uploadDir = "src/main/resources/static/images/pet-photos/" + pet.getId() + "/";
                newPet.setPhotosImagePath(uploadDir.replaceAll("src/main/resources/static", "") + fileName);
                File file = new File("src/main/resources/static" + pet.getPhotosImagePath());
                file.delete();
                pet.updatePet(newPet);
                petRepository.save(pet);
                actualPetId = petId;
            } else {
                Pet savedImage = petRepository.save(newPet);
                uploadDir = "src/main/resources/static/images/pet-photos/" + savedImage.getId() + "/";
                newPet.setPhotosImagePath(uploadDir.replaceAll("src/main/resources/static", "") + fileName);
                petRepository.save(savedImage);
                actualPetId = savedImage.getId();
            }
            FileUploadUtil.saveFile(uploadDir, fileName, byteObjects);
        }

        return "redirect:detail?petId=" + actualPetId;
    }



    @GetMapping("delete")
    public String displayDeletePetProfileForm(Model model, HttpServletRequest request) {
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
                Optional<Pet> result = petRepository.findById(id);
                Pet pet = result.get();
                File file = new File("src/main/resources/static" + pet.getPhotosImagePath());
                File folder = new File("src/main/resources/static/images/pet-photos/" + id);
                file.delete();
                folder.delete();
                if (pet.getMedInfo() != null) {
                    if (pet.getMedInfo().getShotRecords() != null) {
                        for (ShotRecord shotRecord : pet.getMedInfo().getShotRecords()) {
                            shotRecordRepository.delete(shotRecord);
                        }
                    }
                    if (pet.getMedInfo().getPastSurgeries() != null) {
                        for (PastSurgery pastSurgery : pet.getMedInfo().getPastSurgeries()) {
                            pastSurgeryRepository.delete(pastSurgery);
                        }
                    }
                }

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

