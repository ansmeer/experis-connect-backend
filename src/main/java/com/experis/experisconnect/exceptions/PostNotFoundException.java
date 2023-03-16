package com.experis.experisconnect.exceptions;

public class PostNotFoundException extends EntityNotFoundException{
    public PostNotFoundException(int id) {
        super("Post does not exist with ID: " + id);
    }
}
