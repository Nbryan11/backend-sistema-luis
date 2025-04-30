package com.example.SistemaFacturacion.dtoS.factura;

import lombok.Data;

@Data
public class ItemFacturaRequestDTO {
    private Integer productoId;
    private Integer cantidad;

    public Integer getProductoId() {
        return productoId;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setProductoId(Integer productoId) {
        this.productoId = productoId;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
}
