package org.launchcode.PetLife.models;


import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class PetMedInfo {

    @Id
    @GeneratedValue
    private int id;
    private static int nextId = 1;

    private String shotRecords;

    private String pastSurgeries;

    private String species;

    private String currentMeds;

    private String pastMedicalConcerns;

    public PetMedInfo(String shotRecords, String pastSurgeries, String species, String currentMeds, String pastMedicalConcerns) {
        super();
        this.shotRecords = shotRecords;
        this.pastSurgeries = pastSurgeries;
        this.species = species;
        this.currentMeds = currentMeds;
        this.pastMedicalConcerns = pastMedicalConcerns;
    }

    public PetMedInfo() {
        this.id = nextId;
        this.nextId++;
    }

    public String getShotRecords() {
        return shotRecords;
    }

    public void setShotRecords(String shotRecords) {
        this.shotRecords = shotRecords;
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
