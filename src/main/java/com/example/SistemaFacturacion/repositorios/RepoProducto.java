package com.example.SistemaFacturacion.repositorios;

import com.example.SistemaFacturacion.modelos.cliente.ClienteEntity;
import com.example.SistemaFacturacion.modelos.producto.ProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepoProducto extends JpaRepository<ProductoEntity, Integer> {
}
