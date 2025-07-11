package com.rvn14.cores.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SignupForm {
    @NotBlank(message = "Full name is required")
    @Size(max = 40, message = "Full name must be less than 40 characters")
    private String fullName;

    @NotBlank(message = "Username is required")
    @Pattern(regexp = "^[a-zA-Z0-9_]{3,20}$", message = "Username must be 3-20 characters, letters, numbers, or underscores")
    private String username;

    @NotBlank(message = "Email is required")
    @Email(message = "Please enter a valid email address")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters")
    private String password;

    @NotBlank(message = "Please confirm your password")
    private String confirmPassword;

    @AssertTrue(message = "You must accept the terms")
    private Boolean terms;



}
