package com.gusgd.empacotamento.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gusgd.empacotamento.dto.PackingRequest;
import com.gusgd.empacotamento.dto.PackingRequest.Dimensions;
import com.gusgd.empacotamento.dto.PackingRequest.PedidoRequest;
import com.gusgd.empacotamento.dto.PackingRequest.ProductRequest;
import com.gusgd.empacotamento.model.Box;
import com.gusgd.empacotamento.model.BoxType;
import com.gusgd.empacotamento.model.Product;
import com.gusgd.empacotamento.service.PackingService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PackingController.class)
@WithMockUser
@AutoConfigureMockMvc(addFilters = false) 
class PackingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PackingService packingService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldReturnPackedBoxes() throws Exception {
        var dimensions = new Dimensions(10, 20, 30);
        var productRequest = new ProductRequest();
        productRequest.setProduto_id("P1");
        productRequest.setDimensoes(dimensions);

        var pedido = new PedidoRequest();
        pedido.setPedido_id(1);
        pedido.setProdutos(List.of(productRequest));

        var packingRequest = new PackingRequest();
        packingRequest.setPedidos(List.of(pedido));

        // Mock do service
        var box = new Box(BoxType.SMALL, 30, 40, 80, List.of(
                new Product("P1", 10, 20, 30)
        ));

        when(packingService.packOrder(List.of(
                new Product("P1", 10, 20, 30)
        ))).thenReturn(List.of(box));


        mockMvc.perform(post("/api/packing")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(packingRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].pedidoId").value(1))
                .andExpect(jsonPath("$[0].caixas[0].caixaId").value("Caixa 1"))
                .andExpect(jsonPath("$[0].caixas[0].produtos[0]").value("P1"));
    }
}