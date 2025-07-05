package com.fawry.ecommerce;

import java.time.LocalDate;
import com.fawry.ecommerce.model.Cart;
import com.fawry.ecommerce.model.Customer;
import com.fawry.ecommerce.model.ExpirableProduct;
import com.fawry.ecommerce.model.NonExpirableProduct;
import com.fawry.ecommerce.service.ECommerceSystem;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== E-Commerce System Demo ===\n");

        // Create e-commerce system
        ECommerceSystem ecommerce = new ECommerceSystem();

        // Create products
        // Requires shipping and expirable
        ExpirableProduct cheese =
                new ExpirableProduct("Cheese", 100, 10, LocalDate.now().plusDays(30), true, 200.0);
        ExpirableProduct biscuits =
                new ExpirableProduct("Biscuits", 150, 5, LocalDate.now().plusDays(15), true, 0.0);

        // Requires shipping and not expirable
        NonExpirableProduct tv = new NonExpirableProduct("TV", 500, 3, true, 15000); // 15Kg
        NonExpirableProduct mobile = new NonExpirableProduct("Mobile", 300, 8, true, 200); // 200g

        // No shipping but expirable
        ExpirableProduct scratchCard = new ExpirableProduct("Mobile Scratch Card", 50, 20,
                LocalDate.now().plusDays(365), false, 0);

        // Or Windows keys for example both do not expire and don't require shipping
        NonExpirableProduct digitalVideoGame =
                new NonExpirableProduct("Digital Video Game", 70, 20, false, 0);

        // Create customer
        Customer customer = new Customer("Mohy Elden", 1000);

        // Create cart
        Cart cart = new Cart();

        System.out.println("=== Test Case 1: Normal Checkout ===");
        try {
            cart.add(cheese, 2);
            cart.add(tv, 1);
            cart.add(digitalVideoGame, 1);

            System.out.println("Cart before checkout:");
            System.out.println(cart);
            System.out.println("\nCustomer: " + customer);
            System.out.println();

            ecommerce.checkout(customer, cart);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\n=== Test Case 2: Empty Cart Error ===");
        try {
            Cart emptyCart = new Cart();
            ecommerce.checkout(customer, emptyCart);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\n=== Test Case 3: Insufficient Balance Error ===");
        try {
            Customer poorCustomer = new Customer("Poor Customer", 50);
            Cart poorCart = new Cart();
            poorCart.add(cheese, 1);
            ecommerce.checkout(poorCustomer, poorCart);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\n=== Test Case 4: Out of Stock Error ===");
        try {
            Customer richCustomer = new Customer("Rich Customer", 1000);
            Cart stockCart = new Cart();
            stockCart.add(cheese, 15); // More than available
            ecommerce.checkout(richCustomer, stockCart);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\n=== Test Case 5: Expired Product Error ===");
        try {
            ExpirableProduct expiredCheese =
                    new ExpirableProduct("Expired Cheese", 50, 5, LocalDate.now().minusDays(1));
            Customer testCustomer = new Customer("Test Customer", 1000);
            Cart expiredCart = new Cart();
            expiredCart.add(expiredCheese, 1);
            ecommerce.checkout(testCustomer, expiredCart);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }
}
