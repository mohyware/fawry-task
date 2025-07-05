package com.fawry.ecommerce.model;

public class NonExpirableProduct extends Product {

    public NonExpirableProduct(String name, double price, int quantity, boolean requiresShipping,
            double weight) {
        super(name, price, quantity, requiresShipping, weight);
    }

    @Override
    public boolean isExpirable() {
        return false;
    }

    @Override
    public String toString() {
        String shippingInfo = requiresShipping() ? " (Shipping: " + getWeight() + "g)"
                : " (No shipping required)";
        return super.toString() + shippingInfo;
    }
}
