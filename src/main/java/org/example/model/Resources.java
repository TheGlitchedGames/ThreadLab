package org.example.model;

public class Resources {
    private int id;
    private int quantity;
    private int maxQuantity;
    private int minQuantity;

    public Resources(int id, int maxQuantity, int minQuantity) {
        this.id = id;
        this.maxQuantity = maxQuantity;
        this.minQuantity = minQuantity;
        this.quantity = minQuantity;
    }

    public synchronized boolean addResource() {
        if (quantity < maxQuantity) {
            quantity ++;
            return true;
        }
        return false;
    }

    public synchronized boolean removeResource() {
        if (quantity > minQuantity) {
            quantity--;
            return true;
        }
        return false;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getMaxQuantity() {
        return maxQuantity;
    }
    public void setMaxQuantity(int maxQuantity) {
        this.maxQuantity = maxQuantity;
    }

    public int getMinQuantity() {
        return minQuantity;
    }
    public void setMinQuantity(int minQuantity) {
        this.minQuantity = minQuantity;
    }
}
