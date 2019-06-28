package com.tgreenidge.ask.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

public class WelcomeRequestHandler implements RequestHandler {
    @Override
    public boolean canHandle(HandlerInput handlerInput) {
        return handlerInput.matches(intentName("WelcomeIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput) {
        return handlerInput.getResponseBuilder()
                .withSpeech("The Chef Mate Skill allows you to get a recipe that was created on the Chef mate website. " +
                        "You can say Alexa, ask Chef mate to get me the Honey Glazed Salmon Recipe. " +
                        "Or, Alexa, ask Chef Mate to tell me the prep time for this recipe. " +
                        "You can also say Alexa, ask Chef mate to tell me the ingredients, or tell me the steps.")
                .build();
    }
}
