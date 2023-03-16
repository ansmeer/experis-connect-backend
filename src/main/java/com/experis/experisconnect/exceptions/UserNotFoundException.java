package com.experis.experisconnect.exceptions;

public class UserNotFoundException extends EntityNotFoundException{
    public UserNotFoundException(String id){
        super("User does not exist with ID: " + id);
    }
}
