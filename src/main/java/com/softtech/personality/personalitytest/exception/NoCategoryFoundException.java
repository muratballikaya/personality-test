package com.softtech.personality.personalitytest.exception;

public class NoCategoryFoundException extends RuntimeException{

    public NoCategoryFoundException(){
        super("No category found with related credentials.");
    }
}
