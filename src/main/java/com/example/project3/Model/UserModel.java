package com.example.project3.Model;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserModel {


    @NotEmpty(message = "Id must not be empty")
    private String id;

    @NotEmpty(message = "Username must not be empty")
    @Size(min = 5, message = "Username length must be more than 5")
    private String username;

    @NotEmpty(message = "Password must not be empty")
    @Size(min = 6, message = "Password length must be more than 6")
    @Pattern(regexp = "(.+)(\\d+)(\\d|.)*|(\\d+)(.+)(\\d|.)*",message = "Password must be contains characters and digits")
    private String password;

    @NotEmpty(message = "Email must not be empty")
    @Email(message = "Email must be a valid format")
    private String email;


    @NotEmpty(message = "Role must not be empty")
    @Pattern(regexp = "admin|customer",message = "Role must be admin or customer")
    private String role;

    @NotNull(message = "Balance must not be empty ")
    @Positive(message = "Balance must be a positive number")
    private Integer balance;





}
