package org.launchcode.PetLife.models;


import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Entity
public class MedInfo extends AbstractEntity {

    private String currentMeds;

    private String spayNeuter;

    private String chip;
    @OneToMany
//    @JoinColumn(name = "medInfo_id")
    @Valid
    private List<ShotRecord> shotRecords = new ArrayList<ShotRecord>(Arrays.asList(new ShotRecord()));

    @OneToOne(cascade = CascadeType.ALL)
    @Valid
    private PastSurgery pastSurgery;

    @Size(max = 500, message = "Limit up to 500 characters.")
    private String medicalNote;

    public MedInfo(String currentMeds, String spayNeuter, String chip, List<ShotRecord> shotRecords, PastSurgery pastSurgery, String medicalNote) {
        this.currentMeds = currentMeds;
        this.spayNeuter = spayNeuter;
        this.chip = chip;
        this.shotRecords = shotRecords;
        this.pastSurgery = pastSurgery;
        this.medicalNote = medicalNote;
    }

    public MedInfo() {
    }

    public String getSpayNeuter() {
        return spayNeuter;
    }

    public void setSpayNeuter(String spayNeuter) {
        this.spayNeuter = spayNeuter;
    }

    public String getChip() {
        return chip;
    }

    public void setChip(String chip) {
        this.chip = chip;
    }

    public List<ShotRecord> getShotRecords() {
        return shotRecords;
    }

    public void setShotRecords(ArrayList<ShotRecord> shotRecords) {
        this.shotRecords = shotRecords;
    }

    public PastSurgery getPastSurgery() {
        return pastSurgery;
    }

    public void setPastSurgery(PastSurgery pastSurgery) {
        this.pastSurgery = pastSurgery;
    }

    public String getCurrentMeds() {
        return currentMeds;
    }

    public void setCurrentMeds(String currentMeds) {
        this.currentMeds = currentMeds;
    }

    public String getMedicalNote() {
        return medicalNote;
    }

    public void setMedicalNote(String medicalNote) {
        this.medicalNote = medicalNote;
    }
}
