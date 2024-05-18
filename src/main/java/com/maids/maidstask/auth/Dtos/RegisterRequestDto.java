package com.maids.maidstask.auth.Dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;

public record RegisterRequestDto(

        @NotBlank(message = "Username is required")
        String username,

        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email address")
        String email,


//        @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$",
//                message = "Password must be at least 8 characters long, containing at least one uppercase letter, one lowercase letter, one digit, and one special character")
        @NotBlank(message = "Password is required")
        String password,

        @NotBlank(message = "Address is required")
        @Size(max = 100, message = "Address cannot exceed 100 characters")
        String address,

        @JsonProperty("phone_number")
        @NotBlank(message = "Phone number is required")
        @Pattern(regexp = "^\\+(?:[0-9] ?){6,14}[0-9]$", message = "Invalid phone number format")
        String phoneNumber
) {
}
