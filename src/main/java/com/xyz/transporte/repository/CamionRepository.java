package com.xyz.transporte.repository;

import com.xyz.transporte.model.Camion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CamionRepository extends JpaRepository<Camion, Long> {
}