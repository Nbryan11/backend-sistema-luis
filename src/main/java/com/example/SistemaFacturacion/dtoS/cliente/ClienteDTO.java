package com.example.SistemaFacturacion.dtoS.cliente;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ClienteDTO {
    private Integer id;
    private String nombre;
    private String apellido;
    private String telefono;
    private String documento;
    private LocalDateTime fechaCreacion;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getDocumento() {
        return documento;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }
}
