package com.tgreenidge.ask.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.dispatcher.request.handler.impl.LaunchRequestHandler;
import com.amazon.ask.exception.AskSdkException;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.LaunchRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;

import com.amazon.ask.request.RequestHelper;
import com.google.gson.Gson;
import org.slf4j.Logger;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.amazon.ask.request.Predicates.intentName;
import static org.slf4j.LoggerFactory.getLogger;

public class GetRecipeRequestHandler implements RequestHandler {
    private static Logger logger = getLogger(GetRecipeRequestHandler.class);


    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("GetRecipeIntent"));
    }


    @Override
    public Optional<Response> handle(HandlerInput handlerInput) {
        logger.info("Received unrecognized request: " + handlerInput.getRequestEnvelopeJson());
        RequestHelper requestHelper = RequestHelper.forHandlerInput(handlerInput);

        // Use a helper method to get the slot value wrapped in an Optional.
        Optional<String> recipeName = requestHelper.getSlotValue("title");


        String title = recipeName.map(name -> name.toLowerCase())
                .orElse("Hello World! I'm sorry, I don't yet know your recipe name.");

        String recipeInfo = ApiHelper.getRecipe(title);

        if (title.equals("Hello World! I'm sorry, I don't yet know your recipe name."))
            return handlerInput.getResponseBuilder()
                    .withSpeech(title)
                    .build();

        return handlerInput.getResponseBuilder()
                    .withSpeech(recipeInfo)
                    .build();

    }

}
