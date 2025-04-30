package com.example.SistemaFacturacion.dtoS.factura;

import lombok.Data;

import java.util.List;

@Data
public class FacturaRequestDTO {
    private Integer clienteId;
    private List<ItemFacturaRequestDTO> items;

    public Integer getClienteId() {
        return clienteId;
    }

    public List<ItemFacturaRequestDTO> getItems() {
        return items;
    }

    public void setClienteId(Integer clienteId) {
        this.clienteId = clienteId;
    }

    public void setItems(List<ItemFacturaRequestDTO> items) {
        this.items = items;
    }
}
