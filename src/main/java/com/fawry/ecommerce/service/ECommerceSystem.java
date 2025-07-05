package com.fawry.ecommerce.service;

import java.util.ArrayList;
import java.util.List;
import com.fawry.ecommerce.model.Cart;
import com.fawry.ecommerce.model.CartItem;
import com.fawry.ecommerce.model.Customer;
import com.fawry.ecommerce.model.ExpirableProduct;
import com.fawry.ecommerce.model.Product;
import com.fawry.ecommerce.model.ShippableItem;

public class ECommerceSystem {
    private ShippingService shippingService;

    public ECommerceSystem() {
        this.shippingService = new ShippingService();
    }

    public void checkout(Customer customer, Cart cart) {
        if (cart.isEmpty()) {
            throw new IllegalStateException("Cart is empty");
        }

        // Validate all products are in stock and not expired
        for (CartItem item : cart.getItems()) {
            Product product = item.getProduct();

            if (product.getQuantity() < item.getQuantity()) {
                throw new IllegalStateException(
                        "Product " + product.getName() + " is out of stock");
            }

            if (product.isExpirable()) {
                ExpirableProduct expirableProduct = (ExpirableProduct) product;
                if (expirableProduct.isExpired()) {
                    throw new IllegalStateException(
                            "Product " + product.getName() + " has expired");
                }
            }
        }

        // Calculate totals
        double subtotal = cart.getSubtotal();
        double shippingFees = calculateShippingFees(cart);
        double totalAmount = subtotal + shippingFees;

        // Check customer balance
        if (customer.getBalance() < totalAmount) {
            throw new IllegalStateException("Insufficient balance. Required: $" + totalAmount
                    + ", Available: $" + customer.getBalance());
        }

        // Process payment
        customer.deductAmount(totalAmount);

        // Update product quantities
        for (CartItem item : cart.getItems()) {
            item.getProduct().reduceQuantity(item.getQuantity());
        }

        // Collect items for shipping
        List<ShippableItem> shippableItems = new ArrayList<>();
        for (CartItem item : cart.getItems()) {
            if (item.getProduct().requiresShipping()) {
                shippableItems
                        .add(new ShippableItem(item.getProduct().getName(), item.getTotalWeight()));
            }
        }

        // Process shipping if needed
        if (!shippableItems.isEmpty()) {
            shippingService.processShipment(shippableItems);
        }

        printReceipt(cart, subtotal, shippingFees, totalAmount, customer);

        // Clear cart after successful checkout
        cart.clear();
    }

    private double calculateShippingFees(Cart cart) {
        double totalWeight = 0;
        for (CartItem item : cart.getItems()) {
            if (item.getProduct().requiresShipping()) {
                totalWeight += item.getTotalWeight();
            }
        }

        // Simple shipping calculation: $10 base + $2 per kg
        if (totalWeight == 0) {
            return 0;
        }

        double weightInKg = totalWeight / 1000;
        return 10 + (weightInKg * 2);
    }

    private void printReceipt(Cart cart, double subtotal, double shippingFees, double totalAmount,
            Customer customer) {
        System.out.println("** Checkout receipt **");

        for (CartItem item : cart.getItems()) {
            System.out.println(item.getQuantity() + "x " + item.getProduct().getName() + " $"
                    + item.getProduct().getPrice());
        }

        System.out.println("----------------------");
        System.out.println("Subtotal $" + subtotal);
        System.out.println("Shipping $" + shippingFees);
        System.out.println("Amount $" + totalAmount);
        System.out.println("Customer balance after payment: $" + customer.getBalance());
    }
}
