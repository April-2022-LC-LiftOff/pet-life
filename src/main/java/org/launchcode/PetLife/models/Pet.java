package org.launchcode.PetLife.models;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.awt.image.BufferedImage;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@Entity
public class Pet extends AbstractEntityNameDate {

    @Lob
    @Column(name = "photos", columnDefinition="LONGBLOB")
    private byte[] photos;

    private String photosImagePath;

    private Integer ageYear; //validator is in html

    private Integer ageMonth; //validator is in html

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

    public Pet( byte [] photos, String photosImagePath, Integer ageYear, Integer ageMonth, String bDate, String species, String breed, String sex, String weightUnit, Float weight, String aggressive, String venomous, String color, String behavior, User user) {

        this.photos = photos;
        this.photosImagePath =photosImagePath;
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

    public Pet() {}

    public void updatePet(Pet newPet) {
        this.photos = newPet.getPhotos();
        if (newPet.getPhotosImagePath() != null) {
            this.photosImagePath = newPet.getPhotosImagePath();
        }
        this.setName(newPet.getName());
        this.ageYear = newPet.getAgeYear();
        this.ageMonth = newPet.getAgeMonth();
        this.bDate = newPet.getLocalDate();
        this.species = newPet.getSpecies();
        this.breed = newPet.getBreed();
        this.sex = newPet.getSex();
        this.weightUnit = newPet.getWeightUnit();
        this.weight = newPet.getWeight();
        this.aggressive = newPet.getAggressive();
        this.venomous = newPet.getVenomous();
        this.color = newPet.getColor();
        this.behavior = newPet.getBehavior();
    }

    public float weightConversion () {
        if (this.weightUnit.equals("Pound")) {
            return Math.round(this.weight * 0.454 * 10) / 10;
        } else {
            return Math.round(this.weight * 2.2 * 10) / 10;
        }
    }

    public void setPhotosImagePath(String photosImagePath) {
        this.photosImagePath = photosImagePath;
    }

    public String getPhotosImagePath() {
        return this.photosImagePath;
    }
    public byte[] getPhotos() {
        return photos;
    }

    public void setPhotos(byte[] photos) {
        this.photos = photos;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = capitalizeFirstLetterLowerCaseOthers(name);
//    }

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
//        this.species = species.substring(0,1).toUpperCase() + species.substring(1).toLowerCase();
        this.species = capitalizeFirstLetterLowerCaseOthers(species);
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
//        this.breed = breed.substring(0,1).toUpperCase() + breed.substring(1).toLowerCase();
        this.breed = capitalizeFirstLetterLowerCaseOthers(breed);
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
//        this.color = color.substring(0,1).toUpperCase() + color.substring(1).toLowerCase();
        this.color = capitalizeFirstLetterLowerCaseOthers(color);
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

    @Override
    public String toString() {
        return "Pet{" +
                "photos=" + Arrays.toString(photos) +
                ", photosImagePath='" + photosImagePath + '\'' +
                '}';
    }
}
