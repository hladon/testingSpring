package com.example.demo.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Data
@Entity
public class Member {
    @Id
    int memberId;
    @Pattern(regexp = "[a-zA-Z]+")
    @NotBlank
    String userName;
    @NotBlank
    @Pattern(regexp = "\\w+")
    String password;
    @NotBlank
    @Email
    String email;
    @NotBlank
    @Pattern(regexp = "[a-zA-Z]+\\s[a-zA-Z]+")
    String fullName;
    @NotBlank
    String address;
    @NotBlank
    @Pattern(regexp = "\\+\\d[(]\\d{3}[)]d{3}[-]d{2}[-]d{2}")
    String phone;
    @NotBlank
    @Pattern(regexp = "MALE|FEMALE")
    String gender;
    @NotBlank
    @Past
    LocalDate birtDate;
}
