package com.user.service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private String userId;

    private String name;

    @NotNull(message = "Email is required and must be in the correct format.")
    private String email;

    @NotNull(message = "Please select your gender.")
    private String gender;
}
