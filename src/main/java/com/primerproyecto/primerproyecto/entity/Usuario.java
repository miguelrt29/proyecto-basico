package com.primerproyecto.primerproyecto.entity;

import jakarta.persistence.*;
import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuarios")
@Data
@AllArgsConstructor
@NoArgsConstructor  // 👈 ESTE es el que faltaba
@Builder
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String correo;
    private String clave;

    @Column(name = "fecha_creacion")
    private Timestamp fechaCreacion;
}
