package org.launchcode.PetLife.models;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="pet_bio")
public class PetBio extends AbstractEntity{

    private String petName;
    private Integer petAge;
    private Integer petBirthDate;
    private String petSpecies;
    private String petBreed;
    private String petGender;
    private Integer petWeight;
    private String petColor;

    public PetBio(String petName, Integer petAge, Integer petBirthDate, String petSpecies, String petBreed, String petGender, Integer petWeight, String petColor) {
        this.petName = petName;
        this.petAge = petAge;
        this.petBirthDate = petBirthDate;
        this.petSpecies = petSpecies;
        this.petBreed = petBreed;
        this.petGender = petGender;
        this.petWeight = petWeight;
        this.petColor = petColor;
    }
    public PetBio(){

    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public Integer getPetAge() {
        return petAge;
    }

    public void setPetAge(Integer petAge) {
        this.petAge = petAge;
    }

    public Integer getPetBirthDate() {
        return petBirthDate;
    }

    public void setPetBirthDate(Integer petBirthDate) {
        this.petBirthDate = petBirthDate;
    }

    public String getPetSpecies() {
        return petSpecies;
    }

    public void setPetSpecies(String petSpecies) {
        this.petSpecies = petSpecies;
    }

    public String getPetBreed() {
        return petBreed;
    }

    public void setPetBreed(String petBreed) {
        this.petBreed = petBreed;
    }

    public String getPetGender() {
        return petGender;
    }

    public void setPetGender(String petGender) {
        this.petGender = petGender;
    }

    public Integer getPetWeight() {
        return petWeight;
    }

    public void setPetWeight(Integer petWeight) {
        this.petWeight = petWeight;
    }

    public String getPetColor() {
        return petColor;
    }

    public void setPetColor(String petColor) {
        this.petColor = petColor;
    }

    @Override
    public String toString() {
        return "PetBio{" +
                "petName='" + petName + '\'' +
                ", petAge=" + petAge +
                ", petBirthDate=" + petBirthDate +
                ", petSpecies='" + petSpecies + '\'' +
                ", petBreed='" + petBreed + '\'' +
                ", petGender='" + petGender + '\'' +
                ", petWeight=" + petWeight +
                ", petColor='" + petColor + '\'' +
                '}';
    }
}
