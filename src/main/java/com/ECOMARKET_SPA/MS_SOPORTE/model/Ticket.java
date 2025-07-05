package com.ECOMARKET_SPA.MS_SOPORTE.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ticket")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTicket;

    @Column(length = 10, nullable = false)
    private int idTienda;
    
    @Column(length = 12, nullable = false)
    private String rutCliente;

    @Column(length = 100, nullable = false)
    private String asunto;

    @Column(length = 500, nullable = false)
    private String descripcion;

    @Column(length = 500, nullable = true)
    private String respuesta;
    
    @Enumerated(EnumType.STRING)
    @Column(length = 10, nullable = false)
    private EnumEstadoT estadoticket;
    
    @Column(nullable = false)
    private LocalDate fechaCreacion;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idDevolucion_fk", nullable = true) // Ticket tiene la FK
    @JsonManagedReference
    private Devolucion devolucion;
    
}


