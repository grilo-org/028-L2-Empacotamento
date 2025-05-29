package com.gusgd.empacotamento.model;

public enum BoxType {
    SMALL("Caixa 1"),
    MEDIUM("Caixa 2"),
    LARGE("Caixa 3"),
    NONE("Sem Caixa");

    private final String label;

    BoxType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
