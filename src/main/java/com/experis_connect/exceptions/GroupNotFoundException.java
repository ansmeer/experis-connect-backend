package com.experis_connect.exceptions;

public class GroupNotFoundException extends EntityNotFoundException{
    public GroupNotFoundException(int id) {
        super("Group does not exist with ID: " + id);
    }
}
