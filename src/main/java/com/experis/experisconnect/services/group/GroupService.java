package com.experis.experisconnect.services.group;

import com.experis.experisconnect.models.Groups;
import com.experis.experisconnect.services.CRUDService;

import java.util.Set;

public interface GroupService extends CRUDService<Groups, Integer> {
    Set<Groups> searchResultsWithLimitOffset(String userId, String search, int offset, int limit);
    Groups addUserToGroup(String userId, int groupId);
    Set<Groups> findGroupsWithUser(String userId);
}
