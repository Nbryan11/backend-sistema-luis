package com.example.SistemaFacturacion.dtoS.factura;

import com.example.SistemaFacturacion.dtoS.producto.ProductoDTO;
import lombok.Data;

@Data
public class ItemFacturaResponseDTO {
    private Integer id;
    private ProductoDTO producto;
    private Integer cantidad;
    private Float precioUnitario;
    private Float subtotal;

    public Integer getId() {
        return id;
    }

    public ProductoDTO getProducto() {
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

    public void setProducto(ProductoDTO producto) {
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
