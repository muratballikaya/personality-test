package com.softtech.personality.personalitytest.exception;

public class NoTestFoundException extends RuntimeException{

    public NoTestFoundException(){
        super("No test found, please set up a new test");
    }
}
