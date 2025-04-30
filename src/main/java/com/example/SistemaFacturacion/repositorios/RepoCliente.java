package com.example.SistemaFacturacion.repositorios;

import com.example.SistemaFacturacion.modelos.cliente.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepoCliente extends JpaRepository<ClienteEntity, Integer> {
}
