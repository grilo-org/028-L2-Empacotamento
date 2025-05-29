package com.gusgd.empacotamento.service;

import com.gusgd.empacotamento.model.Box;
import com.gusgd.empacotamento.model.BoxType;
import com.gusgd.empacotamento.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class PackingService {

    private final List<Box> availableBoxes = List.of(
            new Box(BoxType.SMALL, 30, 40, 80, new ArrayList<>()),
            new Box(BoxType.MEDIUM, 80, 50, 40, new ArrayList<>()),
            new Box(BoxType.LARGE, 50, 80, 60, new ArrayList<>())
    );

    public List<Box> packOrder(List<Product> products) {
        List<Box> usedBoxes = new ArrayList<>();
        List<Product> mutableProducts = new ArrayList<>(products);

        mutableProducts.sort(Comparator.comparingDouble(
                p -> -(p.getHeight() * p.getWidth() * p.getLength())
        ));

        for (Product product : mutableProducts) {
            boolean packed = false;

            for (Box box : usedBoxes) {
                if (canFit(box, product)) {
                    box.getProducts().add(product);
                    packed = true;
                    break;
                }
            }

            if (!packed) {
                for (Box availableBox : availableBoxes) {
                    if (canFit(availableBox, product)) {
                        Box newBox = cloneBox(availableBox);
                        newBox.getProducts().add(product);
                        usedBoxes.add(newBox);
                        packed = true;
                        break;
                    }
                }
            }

            if (!packed) {
                Box noBox = new Box(BoxType.NONE, 0, 0, 0, new ArrayList<>());
                noBox.getProducts().add(product);
                usedBoxes.add(noBox);
            }
        }

        return usedBoxes;
    }

    private boolean canFit(Box box, Product product) {
        return product.getHeight() <= box.getHeight() &&
                product.getWidth() <= box.getWidth() &&
                product.getLength() <= box.getLength();
    }

    private Box cloneBox(Box box) {
        return new Box(box.getType(), box.getHeight(), box.getWidth(), box.getLength(), new ArrayList<>());
    }
}
