package com.tgreenidge.ask.handlers;

public class Step {
    long id;
    String description;

    public Step(Long id, String description){
        this.id = id;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString(){
        return description;
    }
}
