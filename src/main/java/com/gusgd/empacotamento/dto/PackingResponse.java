package com.gusgd.empacotamento.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PackingResponse {
    private int pedidoId;
    private List<BoxResponse> caixas;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BoxResponse {
        private String caixaId;
        private List<String> produtos;
    }
}
