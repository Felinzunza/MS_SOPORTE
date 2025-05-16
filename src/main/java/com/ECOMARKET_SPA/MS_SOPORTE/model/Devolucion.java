package com.ECOMARKET_SPA.MS_SOPORTE.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Devolucion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*@ManyToOne
    @JoinColumn(name = "pedido_id", nullable = false)
    private Pedido pedido;*/


    @Column(nullable = false)
    private LocalDate fecha;

    @OneToOne(mappedBy = "devolucion")
    @JsonBackReference
    private Ticket ticket;

    // Getters y Setters

}
