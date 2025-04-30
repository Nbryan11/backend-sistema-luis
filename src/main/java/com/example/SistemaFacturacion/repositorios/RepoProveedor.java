package com.example.SistemaFacturacion.repositorios;

import com.example.SistemaFacturacion.modelos.cliente.ClienteEntity;
import com.example.SistemaFacturacion.modelos.producto.ProveedorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepoProveedor extends JpaRepository<ProveedorEntity, Integer> {
}
