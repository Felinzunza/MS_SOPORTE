package com.ECOMARKET_SPA.MS_SOPORTE.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table (name = "producto_devolucion")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoDevolucion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idProductoDevolucion;

    @Column(length = 10, nullable = false)
    private Integer codProducto;

    @Column(nullable = false)
    private int cantidad;

    @ManyToOne
    @JoinColumn(name = "idDevolucion_fk")
    @JsonBackReference
    private Devolucion devolucion;


}
