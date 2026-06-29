package com.corplearn.inscripcion.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.HashMap;
import java.util.Map;

public abstract class BaseController {

    /**
     * Retorna una respuesta exitosa estandarizada.
     * @param message Mensaje informativo para el frontend.
     * @param data Objeto, lista o entidad que devuelve la base de datos.
     * @param status Código de estado HTTP (OK, CREATED, etc.)
     */
    protected <T> ResponseEntity<Map<String, Object>> createResponse(String message, T data, HttpStatus status) {
        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", System.currentTimeMillis());
        response.put("status", status.value());
        response.put("message", message);
        response.put("data", data);
        return new ResponseEntity<>(response, status);
    }

    /**
     * Retorna una respuesta de error estandarizada.
     * @param error Mensaje de error o excepción.
     * @param status Código de estado HTTP de error (BAD_REQUEST, NOT_FOUND, etc.)
     */
    protected ResponseEntity<Map<String, Object>> createErrorResponse(String error, HttpStatus status) {
        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", System.currentTimeMillis());
        response.put("status", status.value());
        response.put("error", error);
        return new ResponseEntity<>(response, status);
    }
}
