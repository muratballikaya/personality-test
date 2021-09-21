package com.softtech.personality.personalitytest.exception;

public class AtLeastOneFieldShouldBeTypedException extends  RuntimeException{
    public  AtLeastOneFieldShouldBeTypedException(){
        super("At least one field should be fiiled in");
    }
}
