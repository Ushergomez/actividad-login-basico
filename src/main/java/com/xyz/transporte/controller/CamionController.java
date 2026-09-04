package com.xyz.transporte.controller;

import com.xyz.transporte.model.Camion;
import com.xyz.transporte.model.Conductor;
import com.xyz.transporte.repository.CamionRepository;
import com.xyz.transporte.repository.ConductorRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/camiones")
@RequiredArgsConstructor
public class CamionController {

    private final CamionRepository camionRepository;
    private final ConductorRepository conductorRepository;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Camion> registrarCamion(@Valid @RequestBody Camion camion) {
        camion.setId(null);
        camion.setConductor(null);
        Camion camionGuardado = camionRepository.save(camion);
        return ResponseEntity.status(201).body(camionGuardado);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'SUPERVISOR')")
    public List<Camion> listarCamiones() {
        return camionRepository.findAll();
    }

    @PutMapping("/{camionId}/conductor/{conductorId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'SUPERVISOR')")
    public ResponseEntity<Camion> asociarConductor(
            @PathVariable Long camionId,
            @PathVariable Long conductorId) {

        Camion camion = camionRepository.findById(camionId).orElse(null);
        Conductor conductor = conductorRepository.findById(conductorId).orElse(null);

        if (camion == null || conductor == null) {
            return ResponseEntity.notFound().build();
        }

        camion.setConductor(conductor);
        return ResponseEntity.ok(camionRepository.save(camion));
    }
}