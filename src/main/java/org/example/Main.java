package org.example;


public class Main {
    public static void main(String[] args) {

        Even even = new Even();
        Odd odd = new Odd();

        Thread oddThread = new Thread(odd);

        oddThread.start();
        even.start();
    }
}

class ShoeWarehouseFulfillmentCenter {
    public static void main(String[] args) {

        ShoeWarehouse shoeWarehouse = new ShoeWarehouse();
        int totalOrders = 30;
        int numConsumer = totalOrders / 2;

        Thread producerThread = new Thread(() -> {
            try {

                for (int i = 0; i <= totalOrders; i++) {
                    String shoeType = shoeWarehouse.shoeTypes.get(i % shoeWarehouse.shoeTypes.size());
                    int quantity = (int) (Math.random() * 10) + 1;
                    Order order = new Order(i, shoeType, quantity);
                    shoeWarehouse.receiveOrder(order);
                    Thread.sleep(1000);
                }

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread[] consumerThreads = new Thread[numConsumer];
        for (int i = 0; i < numConsumer; i++) {
            consumerThreads[i] = new Thread(() -> {

                try {
                    for (int j = 0; j < 3; j++) {
                        shoeWarehouse.fulfillOrder();
                        Thread.sleep(1000);
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        producerThread.start();

        for (Thread consumerThread : consumerThreads) {
            consumerThread.start();
        }

        try {
            producerThread.join();
            for (Thread consumerThread : consumerThreads) {
                consumerThread.join();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}


