package com.maids.maidstask.auth.Dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

public record LoginRequestDto(
        @NotBlank(message = "Username is required")
        String username,

        @NotBlank(message = "password is required")
        String password

) {
}
