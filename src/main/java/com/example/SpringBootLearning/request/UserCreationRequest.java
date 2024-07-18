package com.example.SpringBootLearning.request;

import java.time.LocalDate;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserCreationRequest {
    private String username;
    private String address;
    private LocalDate dob;
    private String firstname;
    private String lastname;

    @Size(min = 8, message = "PASSWORD_INVALID")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=]).*$", message = "PASSWORD_FORM_INVALID")
    private String password;

    @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "PHONENUMER_INVALID")
    private String phone;

    public UserCreationRequest(
            String username,
            String address,
            LocalDate dob,
            String firstname,
            String lastname,
            String password,
            String phone) {
        this.username = username;
        this.address = address;
        this.dob = dob;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.phone = phone;
    }

    public UserCreationRequest() {}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
