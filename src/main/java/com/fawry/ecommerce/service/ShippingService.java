package com.fawry.ecommerce.service;

import java.util.List;
import com.fawry.ecommerce.model.ShippableItem;

public class ShippingService {

    public void processShipment(List<ShippableItem> items) {
        if (items == null || items.isEmpty()) {
            System.out.println("No items to ship");
            return;
        }

        System.out.println("** Shipment notice **");

        double totalWeight = 0;
        for (ShippableItem item : items) {
            System.out.println(item.getName() + " " + item.getWeight() + "g");
            totalWeight += item.getWeight();
        }

        System.out.println(
                "Total package weight " + String.format("%.1f", totalWeight / 1000) + "kg");
        System.out.println("Shipment processed successfully!");
    }
}
