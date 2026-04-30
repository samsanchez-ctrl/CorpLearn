package com.microservicio.bitacora.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class CalendarioDto {
    private Long id;
    private LocalDateTime fecha;
}
