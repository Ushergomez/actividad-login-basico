package com.xyz.transporte.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "camiones")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Camion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String placa;

    @Column(nullable = false)
    private String tipoVehiculo;

    @ManyToOne
    @JoinColumn(name = "conductor_id")
    private Conductor conductor; // puede quedar null hasta que se asocie
}