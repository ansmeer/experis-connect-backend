package com.experis.experisconnect.exceptions;

public class TopicNotFoundException extends EntityNotFoundException{
    public TopicNotFoundException(int id) {
        super("Topic does not exist with ID: " + id);
    }
}
