package com.tgreenidge.ask.handlers;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Step {
    @JsonProperty
    long id;

    @JsonProperty
    String description;

    @JsonProperty
    String stepNumber;

    public Step() {}

    public Step(Long id, String description, String stepNumber){
        this.id = id;
        this.description = description;
        this.stepNumber = stepNumber;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString(){
        return description;
    }
}
