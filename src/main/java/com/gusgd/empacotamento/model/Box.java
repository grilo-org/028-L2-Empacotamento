package com.gusgd.empacotamento.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Box {
    private BoxType type;
    private double height;
    private double width;
    private double length;
    private List<Product> products;
}
