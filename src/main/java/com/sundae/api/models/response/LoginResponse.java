package com.sundae.api.models.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponse {
    private String token;
    private String refCode;
}
