
package service;

import java.util.LinkedHashMap;
import java.util.Map;

import model.Booking;
import model.ConcessionOrder;

public class ConcessionService {

    private Map<String, ConcessionOrder> orders = new LinkedHashMap<>();

    public ConcessionOrder createOrder(String orderId, Booking booking) {

        if (orderId == null || orderId.trim().isEmpty()) {
            throw new IllegalArgumentException("Order ID is required.");
        }

        if (booking == null || booking.isCancelled()
                || !booking.isPaymentConfirmed()) {
            throw new IllegalStateException(
                    "A confirmed, active booking is required.");
        }

        if (orders.containsKey(orderId)) {
            throw new IllegalArgumentException("Order ID already exists.");
        }

        ConcessionOrder order = new ConcessionOrder(orderId, booking);
        orders.put(orderId, order);

        return order;
    }

    public ConcessionOrder getOrder(String orderId) {
        return orders.get(orderId);
    }

    public void displayOrders() {

        if (orders.isEmpty()) {
            System.out.println("No concession orders.");
            return;
        }

        for (ConcessionOrder order : orders.values()) {
            System.out.println(order);
            System.out.println("----------------------");
        }
    }
}
