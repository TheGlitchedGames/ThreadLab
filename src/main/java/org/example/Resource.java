package org.example;

public class Resource {
    private int quantity;
    private int maxQuantity;
    private int minQuantity;

    public Resource(int maxQuantity, int minQuantity) {
        this.quantity = 0;
        this.maxQuantity = maxQuantity;
        this.minQuantity = minQuantity;
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
        }
    }

    public synchronized void decrement() {
        if (quantity > minQuantity) {
            quantity--;
        }
    }
}
