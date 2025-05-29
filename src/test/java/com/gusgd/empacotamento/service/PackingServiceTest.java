package com.gusgd.empacotamento.service;

import com.gusgd.empacotamento.model.Box;
import com.gusgd.empacotamento.model.Product;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PackingServiceTest {

    private final PackingService packingService = new PackingService();

    @Test
    void shouldPackProductsIntoBoxes() {
        List<Product> products = List.of(
                new Product("P1", 10, 20, 30),
                new Product("P2", 15, 25, 35),
                new Product("P3", 40, 50, 60)
        );

        List<Box> packedBoxes = packingService.packOrder(products);

        assertNotNull(packedBoxes);
        assertFalse(packedBoxes.isEmpty());

        int totalPackedProducts = packedBoxes.stream()
                .mapToInt(box -> box.getProducts().size())
                .sum();

        assertEquals(3, totalPackedProducts);
    }

    @Test
    void shouldHandleEmptyProductList() {
        List<Box> packedBoxes = packingService.packOrder(List.of());
        assertTrue(packedBoxes.isEmpty());
    }

    @Test
    void shouldCreateBoxWhenProductDoesNotFit() {
        List<Product> products = List.of(
                new Product("BigProduct", 1000, 1000, 1000)
        );

        List<Box> packedBoxes = packingService.packOrder(products);

        assertEquals(1, packedBoxes.size());
        assertEquals("Sem Caixa", packedBoxes.get(0).getType().getLabel());
    }
}
