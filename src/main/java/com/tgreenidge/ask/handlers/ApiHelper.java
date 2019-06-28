package com.tgreenidge.ask.handlers;

import com.amazon.ask.exception.AskSdkException;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.stream.Collectors;

public class ApiHelper {

    public static String getRecipe(String title) {
        // get the info from the internet
        boolean success = false;
        Recipe[] recipes = null;
        Recipe recipe = null;
        String ingredients = null;
        String prepTime = null;
        String cookTime = null;
        String steps = null;

        String noRecipeText = title + " recipe was not found on Chef mate";

        try {
            URL url = new URL("http://recipe-app-dev.us-west-2.elasticbeanstalk.com/alexa/recipes/" + title);

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            int responseCode = con.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                // this line of code actually goes to the internet!
                BufferedReader reader = new BufferedReader(new InputStreamReader((con.getInputStream())));

                String recipeString = reader.readLine();
                reader.close();

                Gson gson = new Gson();

                recipes = gson.fromJson(recipeString, Recipe[].class);

                if(recipes.length > 0) {
                    recipe = recipes[0];
                    success = true;
                    prepTime = recipe.prepTime;
                    cookTime = recipe.cookTime;

                    // convert all ingredients to a string
                    ingredients = recipe.ingredients.stream().map(Object::toString).collect(Collectors.joining(" , "));;

                    //convert all steps into a string
                    steps = recipe.steps.stream().map(Object::toString).collect(Collectors.joining(" , "));
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new AskSdkException("500: File not found exception");
        } catch (MalformedURLException e) {
            e.printStackTrace();
            throw new AskSdkException("500: Malformed found exception");
        } catch (IOException e) {
            e.printStackTrace();
            throw new AskSdkException("500: I/O found exception");
        }

        return success == true ? "Here is the recipe for " + title + ". The prep time is " + prepTime + ", the cook time is"
                + cookTime + ". The ingredients required are "
                + ingredients + ". The steps are " + steps + "." : noRecipeText;
    }

    public static String getPrepTime(String title) {
        // get the info from the internet
        boolean success = false;
        String prepTime = null;

        String noRecipeText = title + " recipe was not found on Chef mate";

        try {
            URL url = new URL("http://recipe-app-dev.us-west-2.elasticbeanstalk.com/alexa/recipes/" + title + "/preptime");

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            int responseCode = con.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                // this line of code actually goes to the internet!
                BufferedReader reader = new BufferedReader(new InputStreamReader((con.getInputStream())));

                prepTime = reader.readLine();
                reader.close();

                success = true;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new AskSdkException("500: File not found exception");
        } catch (MalformedURLException e) {
            e.printStackTrace();
            throw new AskSdkException("500: Malformed found exception");
        } catch (IOException e) {
            e.printStackTrace();
            throw new AskSdkException("500: I/O found exception");
        }

        return success ? prepTime : noRecipeText;
    }

    public static String getCookTime(String title) {
        // get the info from the internet
        boolean success = false;
        String cookTime = null;

        String noRecipeText = title + " recipe was not found on Chef mate";

        try {
            URL url = new URL("http://recipe-app-dev.us-west-2.elasticbeanstalk.com/alexa/recipes/" + title + "/cooktime");

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            int responseCode = con.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                // this line of code actually goes to the internet!
                BufferedReader reader = new BufferedReader(new InputStreamReader((con.getInputStream())));

                cookTime = reader.readLine();
                reader.close();

                success = true;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new AskSdkException("500: File not found exception");
        } catch (MalformedURLException e) {
            e.printStackTrace();
            throw new AskSdkException("500: Malformed found exception");
        } catch (IOException e) {
            e.printStackTrace();
            throw new AskSdkException("500: I/O found exception");
        }

        return success ? cookTime : noRecipeText;
    }


}
