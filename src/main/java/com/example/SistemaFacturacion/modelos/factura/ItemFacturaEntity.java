package com.example.SistemaFacturacion.modelos.factura;

import com.example.SistemaFacturacion.modelos.producto.ProductoEntity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "items_factura")
public class ItemFacturaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "factura_id", nullable = false)
    private FacturaEntity factura;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id", nullable = false)
    private ProductoEntity producto;

    private Integer cantidad;
    private Float precioUnitario;
    private Float subtotal;

    public Integer getId() {
        return id;
    }

    public FacturaEntity getFactura() {
        return factura;
    }

    public ProductoEntity getProducto() {
        return producto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public Float getPrecioUnitario() {
        return precioUnitario;
    }

    public Float getSubtotal() {
        return subtotal;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setFactura(FacturaEntity factura) {
        this.factura = factura;
    }

    public void setProducto(ProductoEntity producto) {
        this.producto = producto;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public void setPrecioUnitario(Float precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public void setSubtotal(Float subtotal) {
        this.subtotal = subtotal;
    }
}
