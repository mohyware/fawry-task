package com.fawry.ecommerce.model;

public class CartItem {
    private Product product;
    private int quantity;

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalPrice() {
        return product.getPrice() * quantity;
    }

    public double getTotalWeight() {
        return product.getWeight() * quantity;
    }

    @Override
    public String toString() {
        return quantity + "x " + product.getName() + " $" + product.getPrice() + " each = $"
                + getTotalPrice();
    }
}
