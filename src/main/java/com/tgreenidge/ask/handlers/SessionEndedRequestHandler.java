package com.tgreenidge.ask.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.SessionEndedRequest;

import java.util.Optional;

import static com.amazon.ask.request.Predicates.requestType;

// source: https://github.com/alexa/alexa-skills-kit-sdk-for-java/blob/2.0.x/samples/helloworldservlet/src/main/java/com/amazon/ask/helloworldservlet/handlers/SessionEndedRequestHandler.java
public class SessionEndedRequestHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(requestType(SessionEndedRequest.class));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        // any cleanup logic goes here
        return input.getResponseBuilder().build();
    }

}
