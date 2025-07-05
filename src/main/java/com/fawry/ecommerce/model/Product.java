package com.fawry.ecommerce.model;

public abstract class Product {
    private String name;
    private double price;
    private int quantity;
    private boolean requiresShipping;
    private double weight;

    public Product(String name, double price, int quantity, boolean requiresShipping,
            double weight) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.requiresShipping = requiresShipping;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void reduceQuantity(int amount) {
        if (amount > quantity) {
            throw new IllegalArgumentException("Cannot reduce quantity by more than available");
        }
        this.quantity -= amount;
    }

    public boolean requiresShipping() {
        return requiresShipping;
    }

    public double getWeight() {
        return weight;
    }

    public abstract boolean isExpirable();

    @Override
    public String toString() {
        return name + " - $" + price + " (Qty: " + quantity + ")";
    }
}
