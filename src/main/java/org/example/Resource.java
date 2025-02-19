package org.example;

public class Resource {
    private String id;
    private int quantity;
    private int maxQuantity;
    private int minQuantity;

    public Resource(String id, int maxQuantity, int minQuantity) {
        this.id = id;
        this.quantity = 0;
        this.maxQuantity = maxQuantity;
        this.minQuantity = minQuantity;
    }

    public String getId() {
        return id;
    }

    public synchronized int getQuantity() {
        return quantity;
    }

    public synchronized int getMaxQuantity() {
        return maxQuantity;
    }

    public synchronized void setMaxQuantity(int maxQuantity) {
        this.maxQuantity = maxQuantity;
    }

    public synchronized int getMinQuantity() {
        return minQuantity;
    }
    public synchronized void setMinQuantity (int minQuantity) {
        this.minQuantity = minQuantity;
    }

    public synchronized void increment() {
        if (quantity < maxQuantity) {
            quantity++;
            notifyAll(); // Notifica a los consumidores que pueden consumir
        }
    }

    public synchronized void decrement() {
        if (quantity > minQuantity) {
            quantity--;
            notifyAll(); // Notifica a los productores que pueden producir más
        }
    }

}
