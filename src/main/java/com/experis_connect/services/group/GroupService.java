package com.experis_connect.services.group;

import com.experis_connect.models.Groups;
import com.experis_connect.services.CRUDService;

import java.util.Set;

public interface GroupService extends CRUDService<Groups, Integer> {
    Set<Groups> searchResultsWithLimitOffset(String name, int page, int size);
    Groups addUserToGroup(String userId, int groupId);
}
