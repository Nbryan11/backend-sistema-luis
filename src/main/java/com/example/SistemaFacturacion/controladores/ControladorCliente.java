package com.example.SistemaFacturacion.controladores;

import com.example.SistemaFacturacion.dtoS.cliente.ClienteDTO;
import com.example.SistemaFacturacion.modelos.cliente.ClienteEntity;
import com.example.SistemaFacturacion.repositorios.RepoCliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/clientes")
public class ControladorCliente {
    @Autowired
    private RepoCliente clienteRepository;

    @GetMapping
    public List<ClienteDTO> obtenerTodosClientes() {
        return clienteRepository.findAll().stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> obtenerClientePorId(@PathVariable Integer id) {
        return clienteRepository.findById(id)
                .map(this::convertirADTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ClienteDTO crearCliente(@RequestBody ClienteDTO clienteDTO) {
        ClienteEntity cliente = convertirAEntidad(clienteDTO);
        cliente.setFechaCreacion(LocalDateTime.now());
        return convertirADTO(clienteRepository.save(cliente));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> actualizarCliente(@PathVariable Integer id, @RequestBody ClienteDTO clienteDTO) {
        return clienteRepository.findById(id)
                .map(clienteExistente -> {
                    clienteExistente.setNombre(clienteDTO.getNombre());
                    clienteExistente.setApellido(clienteDTO.getApellido());
                    clienteExistente.setTelefono(clienteDTO.getTelefono());
                    clienteExistente.setDocumento(clienteDTO.getDocumento());
                    return ResponseEntity.ok(convertirADTO(clienteRepository.save(clienteExistente)));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable Integer id) {
        clienteRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private ClienteDTO convertirADTO(ClienteEntity cliente) {
        ClienteDTO dto = new ClienteDTO();
        dto.setId(cliente.getId());
        dto.setNombre(cliente.getNombre());
        dto.setApellido(cliente.getApellido());
        dto.setTelefono(cliente.getTelefono());
        dto.setDocumento(cliente.getDocumento());
        dto.setFechaCreacion(cliente.getFechaCreacion());
        return dto;
    }

    private ClienteEntity convertirAEntidad(ClienteDTO dto) {
        ClienteEntity cliente = new ClienteEntity();
        cliente.setNombre(dto.getNombre());
        cliente.setApellido(dto.getApellido());
        cliente.setTelefono(dto.getTelefono());
        cliente.setDocumento(dto.getDocumento());
        return cliente;
    }
    }

