package com.tgreenidge.ask.handlers;

public class Ingredient {
    long id;
    String name;
    String quantity;

    public Ingredient(Long id, String name, String quantity) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public String getQuantity() {
        return quantity;
    }

    @Override
    public String toString(){
        return quantity + " of " + name;
    }
}
