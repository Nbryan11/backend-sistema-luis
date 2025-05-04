package com.example.SistemaFacturacion.controladores;

import com.example.SistemaFacturacion.dtoS.factura.FacturaRequestDTO;
import com.example.SistemaFacturacion.dtoS.factura.FacturaResponseDTO;
import com.example.SistemaFacturacion.dtoS.factura.ItemFacturaResponseDTO;
import com.example.SistemaFacturacion.dtoS.producto.ProductoDTO;
import com.example.SistemaFacturacion.modelos.factura.FacturaEntity;
import com.example.SistemaFacturacion.modelos.factura.ItemFacturaEntity;
import com.example.SistemaFacturacion.modelos.producto.ProductoEntity;
import com.example.SistemaFacturacion.repositorios.RepoCliente;
import com.example.SistemaFacturacion.repositorios.RepoFactura;
import com.example.SistemaFacturacion.repositorios.RepoItemFactura;
import com.example.SistemaFacturacion.repositorios.RepoProducto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/facturas")
public class ControladorFactura {
    @Autowired
    private RepoFactura facturaRepository;

    @Autowired
    private RepoItemFactura itemFacturaRepository;

    @Autowired
    private RepoCliente clienteRepository;

    @Autowired
    private RepoProducto productoRepository;

    @PostMapping
    public ResponseEntity<FacturaResponseDTO> generarFactura(@RequestBody FacturaRequestDTO request) {
        FacturaEntity factura = new FacturaEntity();
        factura.setFechaCreacion(LocalDate.now());

        // Establecer cliente
        factura.setCliente(clienteRepository.findById(request.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado")));

        // Guardar factura primero para obtener ID
        FacturaEntity facturaGuardada = facturaRepository.save(factura);

        // Procesar items
        List<ItemFacturaEntity> items = request.getItems().stream()
                .map(itemRequest -> {
                    ProductoEntity producto = productoRepository.findById(itemRequest.getProductoId())
                            .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

                    ItemFacturaEntity item = new ItemFacturaEntity();
                    item.setFactura(facturaGuardada);
                    item.setProducto(producto);
                    item.setCantidad(itemRequest.getCantidad());
                    item.setPrecioUnitario(producto.getPrecio());
                    item.setSubtotal(producto.getPrecio() * itemRequest.getCantidad());

                    // Actualizar stock
                    producto.setStock(producto.getStock() - itemRequest.getCantidad());
                    productoRepository.save(producto);

                    return item;
                }).collect(Collectors.toList());

        // Guardar items
        itemFacturaRepository.saveAll(items);

        // Calcular y actualizar total
        float total = items.stream()
                .map(ItemFacturaEntity::getSubtotal)
                .reduce(0f, Float::sum);

        facturaGuardada.setTotal(total);
        facturaGuardada.setItems(items);
        FacturaEntity facturaFinal = facturaRepository.save(facturaGuardada);

        return ResponseEntity.ok(convertirADTO(facturaFinal));
    }

    @GetMapping
    public List<FacturaResponseDTO> listarFacturas() {
        return facturaRepository.findAll().stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FacturaResponseDTO> obtenerFactura(@PathVariable Integer id) {
        return facturaRepository.findById(id)
                .map(this::convertirADTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Transactional(readOnly = true)
    private FacturaResponseDTO convertirADTO(FacturaEntity factura) {
        FacturaResponseDTO dto = new FacturaResponseDTO();
        dto.setId(factura.getId());
        dto.setFechaCreacion(factura.getFechaCreacion());
        dto.setTotal(factura.getTotal());

        // Convertir items
        List<ItemFacturaResponseDTO> itemsDTO = factura.getItems().stream()
                .map(this::convertirItemADTO)
                .collect(Collectors.toList());
        dto.setItems(itemsDTO);

        return dto;
    }

    private ItemFacturaResponseDTO convertirItemADTO(ItemFacturaEntity item) {
        ItemFacturaResponseDTO itemDTO = new ItemFacturaResponseDTO();
        itemDTO.setId(item.getId());
        itemDTO.setCantidad(item.getCantidad());
        itemDTO.setPrecioUnitario(item.getPrecioUnitario());
        itemDTO.setSubtotal(item.getSubtotal());

        // Convertir producto
        ProductoDTO productoDTO = new ProductoDTO();
        productoDTO.setId(item.getProducto().getId());
        productoDTO.setNombre(item.getProducto().getNombre());
        productoDTO.setPrecio(item.getProducto().getPrecio());

        itemDTO.setProducto(productoDTO);
        return itemDTO;
    }
}
