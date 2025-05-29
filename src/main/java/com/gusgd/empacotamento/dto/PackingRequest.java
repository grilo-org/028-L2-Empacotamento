package com.gusgd.empacotamento.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
public class PackingRequest {
    private List<PedidoRequest> pedidos;

    @Data
    public static class PedidoRequest {
        private int pedido_id;
        private List<ProductRequest> produtos;
    }

    @Data
    public static class ProductRequest {
        private String produto_id;
        private Dimensions dimensoes;
    }

    @Data
    @AllArgsConstructor
    public static class Dimensions {
        private double altura;
        private double largura;
        private double comprimento;
    }
}
