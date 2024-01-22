package com.sundae.api.models.request;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AddUserRequest {

    @NotNull(message = "Full Name cannot be null.")
    @NotBlank(message = "Full Name cannot be blank.")
    @Pattern(regexp = "^([a-zA-Z ])+$", message = " Full name Doesn't contain Number and Special character")
    @Size(min = 2, max = 50, message = "name can't be soo long")
    private String fullName;

    @NotNull(message = "Email cannot be null.")
    @NotBlank(message = "Email cannot be blank.")
    @Pattern(regexp = "^(\\w[a-zA-Z0-9.]+@[a-zA-Z0-9.]+\\.[a-zA-Z]{2,5})$", message = "Please Enter Valid")
    private String email;

    @Pattern(regexp = "^([a-zA-Z0-9 ])+$", message = "Branch Doesn't contain Special character")
    private String branch;

    @Pattern(regexp = "^([a-zA-Z0-9 ])+$", message = "Department Doesn't contain Special character")
    private String department;

    @Pattern(regexp = "^([a-zA-Z0-9 ])+$", message = "Role Doesn't contain Special character")
    private String role;
}
