package com.ejemplo.inventario.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class HomeController {

    @GetMapping("/api/v1/info")
    public Map<String, Object> info() {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("app", "inventario");
        response.put("status", "running");
        response.put("message", "Sistema de inventario y riesgo de quiebre");
        response.put("version", "0.0.1-SNAPSHOT");
        response.put("timestamp", LocalDateTime.now().toString());
        response.put("endpoints", Map.of(
            "health", "/actuator/health",
            "productos", "/api/v1/productos",
            "bodegas", "/api/v1/bodegas",
            "stock", "/api/v1/stock",
            "riesgo", "/api/v1/riesgo"
        ));
        return response;
    }

    @GetMapping("/api/v1/ping")
    public Map<String, String> ping() {
        return Map.of("pong", "true", "timestamp", LocalDateTime.now().toString());
    }
}
