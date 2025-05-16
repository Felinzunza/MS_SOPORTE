package com.ECOMARKET_SPA.MS_SOPORTE.model;


import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "devolucion")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Devolucion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idDevolucion;

    /*@ManyToOne
    @JoinColumn(name = "pedido_id", nullable = false)*/
    private int idPedido; //Por mientras solo guardamos el id del pedido, no la entidad completa

    @OneToOne(mappedBy = "devolucion")
    @JsonBackReference
    private Ticket ticket;

    // Getters y Setters

}
