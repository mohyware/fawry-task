package com.fawry.ecommerce.model;

public class Customer {
    private String name;
    private double balance;

    public Customer(String name, double balance) {
        this.name = name;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public void deductAmount(double amount) {
        if (amount > balance) {
            throw new IllegalArgumentException("Insufficient balance");
        }
        balance -= amount;
    }

    public void addAmount(double amount) {
        balance += amount;
    }

    @Override
    public String toString() {
        return name + " (Balance: $" + balance + ")";
    }
}
