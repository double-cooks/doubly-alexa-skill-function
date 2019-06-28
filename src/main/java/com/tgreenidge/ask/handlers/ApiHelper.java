package com.tgreenidge.ask.handlers;

import com.amazon.ask.exception.AskSdkException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

public class ApiHelper {

    public static String getRecipe(String title) {
        boolean success = false;
        List<Recipe> recipeList = null;
        Recipe recipe = null;
        String ingredients = null;
        String prepTime = null;
        String cookTime = null;
        String steps = null;

        String noRecipeText = title + " recipe was not found on Chef mate";

        try {
            //TODO
            //change the url to prod url
            URL url = new URL("http://recipe-app-dev.us-west-2.elasticbeanstalk.com/alexa/recipes/" + title);

            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            // this line of code actually goes to the internet!
            BufferedReader reader = new BufferedReader(new InputStreamReader((con.getInputStream())));

            String recipeString = reader.readLine();
            reader.close();

            //reference https://www.javatips.net/api/seldon-server-master/server/test/main/io/seldon/general/ItemStorageTest.java
            ObjectMapper mapper = new ObjectMapper();
            TypeReference t = new TypeReference<List<Recipe>>() {};

            recipeList = mapper.readValue(recipeString, t);

            if(recipeList.size() > 0) {
                recipe = recipeList.get(0);
                success = true;
                prepTime = recipe.prepTime;
                cookTime = recipe.cookTime;
                ingredients = recipe.ingredients.stream().map(Object::toString).collect(Collectors.joining(", "));
                steps = recipe.steps.stream().map(Object::toString).collect(Collectors.joining(", "));
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

        return success ? "Here is the recipe for " + title + ". The prep time is " + prepTime + ", the cook time is "
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
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return success ? cookTime : noRecipeText;
    }

    public static String getIngredients(String title) {
        // get the info from the internet
        boolean success = false;
        String ingredients = null;
        List<Ingredient> ingredientsArray = null;

        String noRecipeText = title + " recipe was not found on Chef mate";

        try {
            URL url = new URL("http://recipe-app-dev.us-west-2.elasticbeanstalk.com/alexa/recipes/" + title + "/ingredients");

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            int responseCode = con.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                // this line of code actually goes to the internet!
                BufferedReader reader = new BufferedReader(new InputStreamReader((con.getInputStream())));

                String ingredientsString = reader.readLine();
                reader.close();

                success = true;

                //reference https://www.javatips.net/api/seldon-server-master/server/test/main/io/seldon/general/ItemStorageTest.java
                ObjectMapper mapper = new ObjectMapper();
                TypeReference t = new TypeReference<List<Ingredient>>() {
                };

                ingredientsArray = mapper.readValue(ingredientsString, t);
                // convert all ingredients to a string
                ingredients = ingredientsArray.stream().map(Object::toString).collect(Collectors.joining(", "));

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

        return success ? "Here are the ingredients for " + title + ". " + ingredients
                : noRecipeText;
    }


    public static String getSteps(String title) {
        // get the info from the internet
        boolean success = false;
        String steps = null;
        List<Step> stepsArray = null;

        String noRecipeText = title + " recipe was not found on Chef mate";

        try {
            URL url = new URL("http://recipe-app-dev.us-west-2.elasticbeanstalk.com/alexa/recipes/" + title + "/steps");

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            int responseCode = con.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                // this line of code actually goes to the internet!
                BufferedReader reader = new BufferedReader(new InputStreamReader((con.getInputStream())));

                String stepsString = reader.readLine();
                reader.close();

                success = true;

                //reference https://www.javatips.net/api/seldon-server-master/server/test/main/io/seldon/general/ItemStorageTest.java
                ObjectMapper mapper = new ObjectMapper();
                TypeReference t = new TypeReference<List<Step>>() {};

                stepsArray = mapper.readValue(stepsString, t);

                // convert all steps to a string
                steps = stepsArray.stream().map(Object::toString).collect(Collectors.joining(", "));
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

        return success ? "Here are the steps for " + title + "." + steps : noRecipeText;
    }


}
