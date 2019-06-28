package com.tgreenidge.ask.handlers;


import com.amazon.ask.SkillStreamHandler;
import com.amazon.ask.Skills;

public class DoublyAlexaSkillStreamHandler extends SkillStreamHandler {
    public DoublyAlexaSkillStreamHandler() {
        super(Skills.standard()
                //add all the request handlers for Alex
                .addRequestHandler(new WelcomeRequestHandler())
                .addRequestHandler(new CustomLaunchRequestHandler())
                .addRequestHandler(new GetRecipeRequestHandler())
                .addRequestHandler(new GetCookTimeRequestHandler())
                .addRequestHandler(new GetPrepTimeRequestHandler())
                .build());
    }
}
