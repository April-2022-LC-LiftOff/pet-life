package org.launchcode.PetLife.models;


import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.ArrayList;


@Entity
public class PetMedInfo {

    @Id
    @GeneratedValue
    private int id;
    private static int nextId = 1;

    private ArrayList<ShotRecord> shotRecords;

    private String pastSurgeries;

    private String species;

    private String currentMeds;

    private String pastMedicalConcerns;

    public PetMedInfo(ArrayList<ShotRecord> shotRecords, String pastSurgeries, String species, String currentMeds, String pastMedicalConcerns) {
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

    public ArrayList<ShotRecord> getShotRecords() {
        return shotRecords;
    }

    public void setShotRecords(ShotRecord shotRecords) {
        this.shotRecords.add(shotRecords);
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
