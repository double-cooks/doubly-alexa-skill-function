package com.tgreenidge.ask.handlers;

import java.util.List;

public class Recipe {
    Long id;
    String title;
    String prepTime;
    String cookTime;

    List<Ingredient> ingredients;
    List<Step> steps;

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

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public List<Step> getSteps() {
        return steps;
    }
}
