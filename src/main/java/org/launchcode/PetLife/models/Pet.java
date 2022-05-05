package org.launchcode.PetLife.models;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Pet extends AbstractEntityNameDate {

    @NotBlank(message = "Name is required.")
    @Size(min = 1, max = 50, message = "Name must be between 3 and 50 characters.")
    private String name;


//    @Max(value = 150, message = "Age for year should be smaller than 150.")
//    @Min(value = 0, message = "Age for year should be greater than 0.")
//    @NotNull(message = "Age for year is required.")
    private Integer ageYear;

//    @Max(value = 11, message = "Age for month should be smaller than 11.")
//    @Min(value = 0, message = "Age for month should be greater than 0.")
    private Integer ageMonth;

    private final static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private LocalDate bDate;


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

    public Pet (String name, Integer ageYear, Integer ageMonth, String bDate, String species, String breed, String sex, Integer weight, String aggressive, String venomous, String color, String behavior) {
        this.name = name;
        this.ageYear = ageYear;
        this.ageMonth = ageMonth;
        this.bDate = LocalDate.parse(bDate);
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

    public Integer getAgeYear() {
        return ageYear;
    }

    private Period calculatePeriod() {
        Period period;
        LocalDate todayDate = LocalDate.now();
        if (this.bDate == null) {
            System.out.println(this.getLocalDate());
            period = Period.between(this.getLocalDate(), todayDate);
        } else {
            period = Period.between(this.bDate, todayDate);
        }

        return period;
    }

    public void updateAge () {
        Period period = this.calculatePeriod();
        this.ageYear += period.getYears();
        this.ageMonth += period.getMonths();
    }

    public void setAgeYear(Integer ageYear) {
        if (ageYear == null) {
            this.ageYear = this.calculatePeriod().getYears();
        } else {
            LocalDate todayDate = LocalDate.now();
            this.setDate(null);
            this.ageYear = ageYear;
        }

    }

    public Integer getAgeMonth() {
        return ageMonth;
    }

    public void setAgeMonth(Integer ageMonth) {
        if (ageMonth == null) {
            this.ageMonth = this.calculatePeriod().getMonths();
        } else {
            this.ageMonth = ageMonth;
        }
    }

    public String getBDate() {
        if (this.bDate == null) {
            return null;
        } else {
            return dateFormatter.format(this.bDate);
        }
    }

    public LocalDate getLocaldateBDate() {
        return this.bDate;
    }

    public void setBDate(String bDate) {
        LocalDate todayDate = LocalDate.now();
        this.setDate(dateFormatter.format(todayDate));
        this.bDate = LocalDate.parse(bDate);
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
