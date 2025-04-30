package com.example.SistemaFacturacion.dtoS.factura;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class FacturaResponseDTO {
    private Integer id;
    private LocalDate fechaCreacion;
    private Float total;
    private List<ItemFacturaResponseDTO> items;

    public Integer getId() {
        return id;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public Float getTotal() {
        return total;
    }

    public List<ItemFacturaResponseDTO> getItems() {
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

    public void setItems(List<ItemFacturaResponseDTO> items) {
        this.items = items;
    }
}
