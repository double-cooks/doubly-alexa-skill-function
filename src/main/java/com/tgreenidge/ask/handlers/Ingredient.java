package com.tgreenidge.ask.handlers;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Ingredient {
    @JsonProperty
    long id;

    @JsonProperty
    String name;

    @JsonProperty
    String quantity;

    public Ingredient(){}

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
