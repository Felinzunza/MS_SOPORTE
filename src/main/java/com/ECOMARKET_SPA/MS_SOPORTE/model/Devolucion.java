package com.ECOMARKET_SPA.MS_SOPORTE.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "devolucion")
public class Devolucion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idDevolucion;

    @Column(nullable = true)
    private int idPedido;

    @OneToOne(mappedBy = "devolucion")
    @JsonBackReference
    private Ticket ticket;

    @OneToMany(mappedBy = "devolucion", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<ProductoDevolucion> productosDevolucion;

} 
