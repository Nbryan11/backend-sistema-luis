package com.example.SistemaFacturacion.modelos.factura;

import com.example.SistemaFacturacion.modelos.cliente.ClienteEntity;
import com.example.SistemaFacturacion.modelos.producto.ProductoEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.EqualsAndHashCode;

@Entity
@Data
@Table(name = "facturas")
public class FacturaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "fecha_creacion")
    private LocalDate fechaCreacion;

    private Float total;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    private ClienteEntity cliente;

    @OneToMany(mappedBy = "factura", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemFacturaEntity> items = new ArrayList<>();

    // Método para agregar items (usado desde el ControladorFactura)
    public void agregarItem(ProductoEntity producto, Integer cantidad) {
        ItemFacturaEntity item = new ItemFacturaEntity();
        item.setFactura(this);
        item.setProducto(producto);
        item.setCantidad(cantidad);
        item.setPrecioUnitario(producto.getPrecio());
        item.setSubtotal(producto.getPrecio() * cantidad);
        this.items.add(item);
    }

    public Integer getId() {
        return id;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public Float getTotal() {
        return total;
    }

    public ClienteEntity getCliente() {
        return cliente;
    }

    public List<ItemFacturaEntity> getItems() {
        return items;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public void setCliente(ClienteEntity cliente) {
        this.cliente = cliente;
    }

    public void setItems(List<ItemFacturaEntity> items) {
        this.items = items;
    }
}
