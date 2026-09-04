package com.xyz.transporte.controller;

import com.xyz.transporte.model.Conductor;
import com.xyz.transporte.repository.ConductorRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/conductores")
@RequiredArgsConstructor
public class ConductorController {

    private final ConductorRepository conductorRepository;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Conductor> registrarConductor(@Valid @RequestBody Conductor conductor) {
        conductor.setId(null);
        Conductor conductorGuardado = conductorRepository.save(conductor);
        return ResponseEntity.status(201).body(conductorGuardado);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'SUPERVISOR')")
    public List<Conductor> listarConductores() {
        return conductorRepository.findAll();
    }
}