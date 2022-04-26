package org.launchcode.PetLife.models;


import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
public class PetMedInfo extends AbstractEntity {

    private static int nextId = 1;

    @OneToOne(cascade = CascadeType.ALL)
    private ShotRecord shotRecord;

    private String pastSurgeries;

    private String species;

    private String currentMeds;

    private String pastMedicalConcerns;

    public PetMedInfo(ShotRecord shotRecord, String pastSurgeries, String species, String currentMeds, String pastMedicalConcerns) {
        this.shotRecord = shotRecord;
        this.pastSurgeries = pastSurgeries;
        this.species = species;
        this.currentMeds = currentMeds;
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

    public String getPastSurgeries() {
        return pastSurgeries;
    }

    public void setPastSurgeries(String pastSurgeries) {
        this.pastSurgeries = pastSurgeries;
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
