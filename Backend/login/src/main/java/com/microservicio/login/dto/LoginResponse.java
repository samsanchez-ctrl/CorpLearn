package com.microservicio.login.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {
    private String mensaje;
    private String email;
    private Long rolId;
}
