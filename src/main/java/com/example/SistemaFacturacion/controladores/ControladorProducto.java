package com.example.SistemaFacturacion.controladores;

import com.example.SistemaFacturacion.dtoS.producto.ProductoDTO;
import com.example.SistemaFacturacion.modelos.producto.ProductoEntity;
import com.example.SistemaFacturacion.repositorios.RepoProducto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/productos")
public class ControladorProducto {

    @Autowired
    private RepoProducto productoRepository;

    @GetMapping
    public List<ProductoDTO> listarProductos() {
        return productoRepository.findAll().stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoDTO> obtenerProducto(@PathVariable Integer id) {
        return productoRepository.findById(id)
                .map(this::convertirADTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ProductoDTO registrarProducto(@RequestBody ProductoDTO productoDTO) {
        ProductoEntity producto = convertirAEntidad(productoDTO);
        producto.setFechaCreacion(LocalDateTime.now());
        producto.setActivo(true);
        return convertirADTO(productoRepository.save(producto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoDTO> actualizarProducto(@PathVariable Integer id, @RequestBody ProductoDTO productoDTO) {
        return productoRepository.findById(id)
                .map(productoExistente -> {
                    productoExistente.setNombre(productoDTO.getNombre());
                    productoExistente.setDescripcion(productoDTO.getDescripcion());
                    productoExistente.setPrecio(productoDTO.getPrecio());
                    productoExistente.setStock(productoDTO.getStock());
                    productoExistente.setCodigoBarras(productoDTO.getCodigoBarras());
                    productoExistente.setCategoria(productoDTO.getCategoria());
                    return ResponseEntity.ok(convertirADTO(productoRepository.save(productoExistente)));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/stock")
    public ResponseEntity<ProductoDTO> ajustarStock(@PathVariable Integer id, @RequestParam Integer cantidad) {
        return productoRepository.findById(id)
                .map(producto -> {
                    producto.setStock(producto.getStock() + cantidad);
                    return ResponseEntity.ok(convertirADTO(productoRepository.save(producto)));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> desactivarProducto(@PathVariable Integer id) {
        return productoRepository.findById(id)
                .map(producto -> {
                    producto.setActivo(false);
                    productoRepository.save(producto);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }




    private ProductoDTO convertirADTO(ProductoEntity producto) {
        ProductoDTO dto = new ProductoDTO();
        dto.setId(producto.getId());
        dto.setNombre(producto.getNombre());
        dto.setDescripcion(producto.getDescripcion());
        dto.setPrecio(producto.getPrecio());
        dto.setStock(producto.getStock());
        dto.setFechaCreacion(producto.getFechaCreacion());
        dto.setCodigoBarras(producto.getCodigoBarras());
        dto.setCategoria(producto.getCategoria());
        dto.setActivo(producto.getActivo());
        return dto;
    }

    private ProductoEntity convertirAEntidad(ProductoDTO dto) {
        ProductoEntity producto = new ProductoEntity();
        producto.setNombre(dto.getNombre());
        producto.setDescripcion(dto.getDescripcion());
        producto.setPrecio(dto.getPrecio());
        producto.setStock(dto.getStock());
        producto.setCodigoBarras(dto.getCodigoBarras());
        producto.setCategoria(dto.getCategoria());
        return producto;
    }
}
