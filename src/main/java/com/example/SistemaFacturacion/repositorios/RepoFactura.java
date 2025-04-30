package com.example.SistemaFacturacion.repositorios;

import com.example.SistemaFacturacion.modelos.cliente.ClienteEntity;
import com.example.SistemaFacturacion.modelos.factura.FacturaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepoFactura extends JpaRepository<FacturaEntity, Integer> {
}
