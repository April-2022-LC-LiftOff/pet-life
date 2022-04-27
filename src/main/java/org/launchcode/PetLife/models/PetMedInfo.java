package org.launchcode.PetLife.models;


import lombok.Getter;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;


@Entity
public class PetMedInfo extends AbstractEntity {
    @Size(max = 20, message = "Species must contain less than 20 characters.")
    private String species;

    private String currentMeds;

    @OneToOne(cascade = CascadeType.ALL)
    @Valid
    private ShotRecord shotRecord;

    @OneToOne(cascade = CascadeType.ALL)
    @Valid
    private PastSurgery pastSurgery;

    @Size(max = 500, message = "Limit up to 500 characters.")
    private String pastMedicalConcerns;

    public PetMedInfo(String species, String currentMeds, ShotRecord shotRecord, PastSurgery pastSurgery, String pastMedicalConcerns) {
        this.species = species;
        this.currentMeds = currentMeds;
        this.shotRecord = shotRecord;
        this.pastSurgery = pastSurgery;
        this.pastMedicalConcerns = pastMedicalConcerns;
    }

    public PetMedInfo() {
    }

    public ShotRecord getShotRecord() {
        return shotRecord;
    }

    public void setShotRecord(ShotRecord shotRecord) {
        this.shotRecord = shotRecord;
    }

    public PastSurgery getPastSurgery() {
        return pastSurgery;
    }

    public void setPastSurgery(PastSurgery pastSurgery) {
        this.pastSurgery = pastSurgery;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getCurrentMeds() {
        return currentMeds;
    }

    public void setCurrentMeds(String currentMeds) {
        this.currentMeds = currentMeds;
    }

    public String getPastMedicalConcerns() {
        return pastMedicalConcerns;
    }

    public void setPastMedicalConcerns(String pastMedicalConcerns) {
        this.pastMedicalConcerns = pastMedicalConcerns;
    }
}
