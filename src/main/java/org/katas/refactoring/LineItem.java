package org.katas.refactoring;

public class LineItem {
    private String description;
    private double price;
    private int quantity;

    public LineItem(String description, double price, int quantity) {
        super();
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }

    String getDescription() {
        return description;
    }

    double getPrice() {
        return price;
    }

    int getQuantity() {
        return quantity;
    }

    double getTotalAmount() {
        return price * quantity;
    }
}