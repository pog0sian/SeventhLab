package org.example;

import java.util.LinkedList;
import java.util.List;

public class ShoeWarehouse {

    public static final List<String> shoeTypes = List.of("Кроссовки", "Ботинки", "Туфли");

    private final List<Order> orders = new LinkedList<>();
    private final int maxOrders = 10;

    public synchronized void receiveOrder (Order order) throws InterruptedException {

        while (orders.size() >= maxOrders) {
            System.out.println("Склад полон. Ожидайте");
            wait();
        }
        orders.add(order);
        System.out.println("Заказ получен: " + order);
        notifyAll();
    }

    public synchronized void fulfillOrder() throws InterruptedException {
        while (orders.isEmpty()) {
            System.out.println("Заказ нет. Ожидание");
            wait();
        }
        Order order = orders.removeFirst();
        System.out.println("Заказ " + order + " выполнен");
        notifyAll();
    }
}
