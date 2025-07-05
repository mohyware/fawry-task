package com.fawry.ecommerce.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<CartItem> items;

    public Cart() {
        this.items = new ArrayList<>();
    }

    public void add(Product product, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }

        if (quantity > product.getQuantity()) {
            throw new IllegalArgumentException(
                    "Requested quantity exceeds available stock for " + product.getName());
        }

        // Check if product is already in cart
        for (CartItem item : items) {
            if (item.getProduct().getName().equals(product.getName())) {
                int newTotalQuantity = item.getQuantity() + quantity;
                if (newTotalQuantity > product.getQuantity()) {
                    throw new IllegalArgumentException(
                            "Total quantity in cart would exceed available stock for "
                                    + product.getName());
                }
                item = new CartItem(product, newTotalQuantity);
                return;
            }
        }

        items.add(new CartItem(product, quantity));
    }

    public List<CartItem> getItems() {
        return new ArrayList<>(items);
    }

    public double getSubtotal() {
        return items.stream().mapToDouble(CartItem::getTotalPrice).sum();
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public void clear() {
        items.clear();
    }

    public int getItemCount() {
        return items.size();
    }

    @Override
    public String toString() {
        if (items.isEmpty()) {
            return "Cart is empty";
        }

        StringBuilder sb = new StringBuilder("Cart Contents:\n");
        for (CartItem item : items) {
            sb.append(item.toString()).append("\n");
        }
        sb.append("Subtotal: $").append(getSubtotal());
        return sb.toString();
    }
}
