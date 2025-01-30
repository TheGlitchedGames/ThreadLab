package org.example;

public class MyModel {
    private final Resource resource;

    public MyModel() {
        this.resource = new Resource(100, 0); // Initial values for max and min quantity
    }

    public Resource getResource() {
        return resource;
    }
}