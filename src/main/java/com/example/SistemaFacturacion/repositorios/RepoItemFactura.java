package com.example.SistemaFacturacion.repositorios;

import com.example.SistemaFacturacion.modelos.cliente.ClienteEntity;
import com.example.SistemaFacturacion.modelos.factura.ItemFacturaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepoItemFactura extends JpaRepository<ItemFacturaEntity, Integer> {
}
