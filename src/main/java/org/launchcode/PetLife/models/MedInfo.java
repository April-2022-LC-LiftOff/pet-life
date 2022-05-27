package org.launchcode.PetLife.models;


import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Entity
public class MedInfo extends AbstractEntity {

    @Size(max = 500, message = "Limit up to 500 characters.")
    private String currentMeds;

    private String spayNeuter;

    private String chip;
    @OneToMany
    @JoinColumn(name = "med_info_id")
    private List<ShotRecord> shotRecords = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "med_info_id")
    private List<PastSurgery> pastSurgeries = new ArrayList<>();

    @Size(max = 500, message = "Limit up to 500 characters.")
    private String medicalNote;

    public MedInfo(String currentMeds, String spayNeuter, String chip, List<ShotRecord> shotRecords, List<PastSurgery> pastSurgeries, String medicalNote) {
        this.currentMeds = currentMeds;
        this.spayNeuter = spayNeuter;
        this.chip = chip;
        this.shotRecords = shotRecords;
        this.pastSurgeries = pastSurgeries;
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

    public List<PastSurgery> getPastSurgeries() {
        return pastSurgeries;
    }

    public void setPastSurgeries(List<PastSurgery> pastSurgeries) {
        this.pastSurgeries = pastSurgeries;
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
