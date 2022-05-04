package org.launchcode.PetLife.models;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Pet extends AbstractEntity {

    @NotBlank(message = "Name is required.")
    @Size(min = 1, max = 50, message = "Name must be between 3 and 50 characters.")
    private String name;

    @Max(value = 150, message = "Age should be smaller than 150.")
    @Min(value = 0, message = "Age should be greater than 0.")
    @NotNull(message = "Age is required.")
    private Integer age;

    @Size(min = 1, max = 50, message = "Species must be between 1 and 50 characters.")
    @NotNull(message = "Species is required.")
    private String species;

    @Size(max = 50, message = "Breed must be between 1 and 50 characters.")
    private String breed;
    @NotNull(message = "Sex is required.")
    private String sex;
    @NotNull(message = "The unit for the weight is required.")
    private String weightUnit;
    @Max(value = 3000, message = "Weight should be smaller than 3000.")
    @Min(value = 0, message = "Weight should be greater than 0.")
    @NotNull(message = "Weight is required.")
    private Float weight;

    @NotNull(message = "Color is required.")
    @Size(min = 1, max = 50, message = " Color must be between 1 and 50 characters.")
    private String color;

    @NotNull(message = "Whether this animal is aggressive is required.")
    private String aggressive;
    @NotNull(message = "Whether this animal is venomous is required.")
    private String venomous;

    @Size(max = 20, message = "This field must be less than 200 characters.")
    private String behavior;

    @OneToOne(cascade = CascadeType.ALL)
    private MedInfo medInfo;

    @ManyToOne
    private User user;

    public Pet (String name, Integer age, String species, String breed, String sex, Integer weight, String aggressive, String venomous, String color, String behavior, User user) {
        this.name = name;
        this.age = age;
        this.species = species;
        this.breed = breed;
        this.sex = sex;
        this.weightUnit = weightUnit;
        this.weight = weight;
        this.aggressive = aggressive;
        this.venomous = venomous;
        this.color = color;
        this.behavior = behavior;
        this.user = user;
    }

    public float weightConversion () {
        if (this.weightUnit.equals("Pound")) {
            return Math.round(this.weight * 0.454 * 10) / 10;
        } else {
            return Math.round(this.weight * 2.2 * 10) / 10;
        }
    }

    public Pet() {}

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

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

    public String getWeightUnit() {
        return weightUnit;
    }

    public void setWeightUnit(String weightUnit) {
        this.weightUnit = weightUnit;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
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
