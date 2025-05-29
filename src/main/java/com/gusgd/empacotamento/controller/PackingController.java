package com.gusgd.empacotamento.controller;

import com.gusgd.empacotamento.dto.PackingRequest;
import com.gusgd.empacotamento.dto.PackingResponse;
import com.gusgd.empacotamento.model.Box;
import com.gusgd.empacotamento.model.Product;
import com.gusgd.empacotamento.service.PackingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/packing")
@RequiredArgsConstructor
public class PackingController {

    private final PackingService packingService;

    @PostMapping
    public ResponseEntity<List<PackingResponse>> packOrders(
            @RequestBody PackingRequest packingRequest) {

        List<PackingRequest.PedidoRequest> pedidos = packingRequest.getPedidos();
        if (pedidos == null) {
            pedidos = Collections.emptyList();
        }

        List<PackingResponse> response = pedidos.stream().map(pedido -> {
            List<Product> products = pedido.getProdutos() != null
                    ? pedido.getProdutos().stream().map(prod -> {
                PackingRequest.Dimensions dim = prod.getDimensoes();
                return new Product(
                        prod.getProduto_id(),
                        dim != null ? dim.getAltura() : 0,
                        dim != null ? dim.getLargura() : 0,
                        dim != null ? dim.getComprimento() : 0
                );
            }).collect(Collectors.toList())
                    : Collections.emptyList();

            List<Box> packedBoxes = packingService.packOrder(products);

            List<PackingResponse.BoxResponse> boxResponses = packedBoxes.stream().map(box ->
                    new PackingResponse.BoxResponse(
                            box.getType().getLabel(),
                            box.getProducts().stream()
                                    .map(Product::getId)
                                    .collect(Collectors.toList())
                    )
            ).collect(Collectors.toList());

            return new PackingResponse(pedido.getPedido_id(), boxResponses);
        }).collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }
}
