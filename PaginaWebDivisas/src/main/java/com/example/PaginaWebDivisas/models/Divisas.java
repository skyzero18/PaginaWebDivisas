package com.example.PaginaWebDivisas.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Divisas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "compra", nullable = false)
    private float compra;

    @Column(name = "venta", nullable = false)
    private float venta;

    @Column(name = "status", nullable = false)
    private Boolean status;

}
