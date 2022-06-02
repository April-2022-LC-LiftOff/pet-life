package org.launchcode.PetLife.models;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "reset_password_token")
    private String resetPasswordToken;

    @Column(nullable = false, unique = true, length = 45)
    private String email;

    @Column(nullable = false, length = 64)
    private String password;

    @Column(name = "first_name", nullable = false, length = 20)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 20)
    private String lastName;

    private boolean enabled;
    @Size(max = 80, message = "Address be less than 80 characters.")
    private String residentAddress;
    @Size(max = 80, message = "Address be less than 80 characters.")
    private String clinicAddress;

    private String number;

    private String alternativeNumber;

    public boolean isEnabled() {
        return enabled;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    private Collection<Role> roles;

    @OneToMany
    @JoinColumn(name = "user_id")
    private List<Pet> pets = new ArrayList<>();

    @Transient
    private String isAdmin;

    public void updateUserInfo(User newUser) {
        this.firstName = newUser.getFirstName();
        this.lastName = newUser.getLastName();
        this.residentAddress = newUser.getResidentAddress();
        this.clinicAddress = newUser.getClinicAddress();
        this.number = newUser.getNumber();
        this.alternativeNumber = newUser.getAlternativeNumber();
//        this.isAdmin = newUser.getIsAdmin();
//        this.roles = newUser.getRoles();
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }

    public String getResidentAddress() {
        return residentAddress;
    }

    public void setResidentAddress(String residentAddress) {
        this.residentAddress = residentAddress;
    }

    public String getClinicAddress() {
        return clinicAddress;
    }

    public void setClinicAddress(String clinicAddress) {
        this.clinicAddress = clinicAddress;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAlternativeNumber() {
        return alternativeNumber;
    }

    public void setAlternativeNumber(String alternativeNumber) {
        this.alternativeNumber = alternativeNumber;
    }

    public String getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(String isAdmin) {
        this.isAdmin = isAdmin;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void addPet(Pet pet) {this.pets.add(pet);}

    public List<Pet> getPets() {
        return pets;
    }

    public String getResetPasswordToken() {
        return resetPasswordToken;
    }

    public void setResetPasswordToken(String resetPasswordToken) {
        this.resetPasswordToken = resetPasswordToken;
    }
}
