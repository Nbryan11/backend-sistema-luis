package com.example.SistemaFacturacion.modelos.producto;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "proveedores")
public class ProveedorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;
    private String identificacion;
    private String direccion;
    private String correo;
}
