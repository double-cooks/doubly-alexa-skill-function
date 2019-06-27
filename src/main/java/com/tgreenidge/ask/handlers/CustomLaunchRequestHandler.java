package com.tgreenidge.ask.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.impl.LaunchRequestHandler;
import com.amazon.ask.model.LaunchRequest;
import com.amazon.ask.model.Response;

import java.util.Optional;
import org.slf4j.Logger;


import static com.amazon.ask.request.Predicates.requestType;
import static org.slf4j.LoggerFactory.getLogger;

public class CustomLaunchRequestHandler implements LaunchRequestHandler {
    private static Logger logger = getLogger(CustomLaunchRequestHandler.class);

    @Override
    public boolean canHandle(HandlerInput input, LaunchRequest launchRequest) {
        return input.matches(requestType(LaunchRequest.class));
    }

    @Override
    public Optional<Response> handle(HandlerInput input, LaunchRequest launchRequest) {
        logger.info("Received unrecognized request: " + input.getRequestEnvelopeJson());

        return input.getResponseBuilder()
                .withSpeech("This Doubly Recipes Skill allows you to get a recipe that was created on Doubly. " +
                        " You can say Alexa, ask Doubly to get me the Honey Glazed Salmon Recipe. " +
                        "Or, Alexa, ask Doubly to tell me the prep time for this recipe. " +
                        "You can also say Alexa, ask Doubly to tell me the ingredients, or tell me the next step.")
                .build();
    }
}
