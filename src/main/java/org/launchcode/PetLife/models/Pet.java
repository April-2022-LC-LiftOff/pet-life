package org.launchcode.PetLife.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.*;

@Entity
public class Pet extends AbstractEntity {

    @NotBlank(message = "Name is required")
    @Size(min = 1, max = 50, message = "Name must be between 3 and 50 characters")
    private String name;

    @Max(value = 150, message = "Age should be smaller than 150.")
    @Min(value = 0, message = "Age should be greater than 0.")
    @NotNull(message = "Age is required")
    private Integer age;

    @Size(min = 1, max = 50, message = " Species must be between 1 and 50 characters")
    @NotNull(message = "Species is required")
    private String species;

    @Size(min = 1, max = 50, message = " Breed must be between 1 and 50 characters")
    @NotNull(message = "Breed is required or input unknown")
    private String breed;

    private String sex;

    @Max(value = 3000, message = "Weight should be smaller than 3000.")
    @Min(value = 0, message = "Weight should be greater than 0.")
    @NotNull(message = "Weight is required")
    private Integer weight;

    @NotNull(message = "Color of pet is required")
    @Size(min = 1, max = 50, message = " Color must be between 1 and 50 characters")
    private String color;

    private String aggressive;

    private String venomous;

    @Size(min = 1, max = 200, message = " must be between 1 and 200 characters")
    private String behavior;

    @OneToOne(cascade = CascadeType.ALL)
    private MedInfo medInfo;

    public Pet (String name, Integer age, String species, String breed, String sex, Integer weight, String aggressive, String venomous, String color, String behavior) {
        this.name = name;
        this.age = age;
        this.species = species;
        this.breed = breed;
        this.sex = sex;
        this.weight = weight;
        this.aggressive = aggressive;
        this.venomous = venomous;
        this.color = color;
        this.behavior = behavior;
    }

    public Pet() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getAggressive() {
        return aggressive;
    }

    public void setAggressive(String aggressive) {
        this.aggressive = aggressive;
    }

    public String getVenomous() {
        return venomous;
    }

    public void setVenomous(String venomous) {
        this.venomous = venomous;
    }

    public String getBehavior() {
        return behavior;
    }

    public void setBehavior(String behavior) {
        this.behavior = behavior;
    }

    public MedInfo getMedInfo() {
        return medInfo;
    }

    public void setMedInfo(MedInfo medInfo) {
        this.medInfo = medInfo;
    }
}
