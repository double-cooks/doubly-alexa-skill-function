package com.tgreenidge.ask.handlers;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Recipe {
    @JsonProperty
    Long id;

    @JsonProperty
    String title;

    @JsonProperty
    String prepTime;

    @JsonProperty
    String cookTime;

    @JsonProperty
    List<Ingredient> ingredients;

    @JsonProperty
    List<Step> steps;

    public Recipe(){}

    public Recipe(Long id, String title, String prepTime, String cookTime, List<Ingredient> ingredients, List<Step> steps ){
        this.title = title.toLowerCase();
        this.prepTime = prepTime;
        this.cookTime = cookTime;
        this.steps = steps;
        this.ingredients = ingredients;
    }

    public String getTitle() {
        return title;
    }

    public String getPrepTime() {
        return prepTime;
    }

    public String getCookTime() {
        return cookTime;
    }

    @JsonIgnore
    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    @JsonIgnore
    public List<Step> getSteps() {
        return steps;
    }
}
