package com.fawry.ecommerce.model;

public class ShippableItem {
    private String name;
    private double weight;

    public ShippableItem(String name, double weight) {
        this.name = name;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }

    public String toString() {
        return name + " (" + weight + "g)";
    }
}
