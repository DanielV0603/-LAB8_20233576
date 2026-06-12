package org.example.lab8.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "equipo")
@Getter
@Setter
public class Equipo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 45)
    private String nombre;

    @Column(name = "tag", nullable = false, length = 45)
    private String tag;

    @Column(name = "capitan", nullable = false, length = 45)
    private String capitan;

    @Column(name = "jugadores")
    private Integer jugadores;

    @Column(name = "juego", nullable = false, length = 45)
    private String juego;

    @Column(name = "estado", nullable = false)
    private String estado;

    @Column(name = "pais", nullable = false, length = 45)
    private String pais;

    @Column(name = "correo", length = 100)
    private String correo;

    @Column(name = "telefono", length = 45)
    private String telefono;
}
