package com.fawry.ecommerce.model;

import java.time.LocalDate;

public class ExpirableProduct extends Product {
    private LocalDate expiryDate;

    public ExpirableProduct(String name, double price, int quantity, LocalDate expiryDate) {
        this(name, price, quantity, expiryDate, true, 200.0); // Default: requires shipping, 200g
    }

    public ExpirableProduct(String name, double price, int quantity, LocalDate expiryDate,
            boolean requiresShipping, double weight) {
        super(name, price, quantity, requiresShipping, weight);
        this.expiryDate = expiryDate;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public boolean isExpired() {
        return LocalDate.now().isAfter(expiryDate);
    }

    @Override
    public boolean isExpirable() {
        return true;
    }

    @Override
    public String toString() {
        return super.toString() + " (Expires: " + expiryDate + ")";
    }
}
