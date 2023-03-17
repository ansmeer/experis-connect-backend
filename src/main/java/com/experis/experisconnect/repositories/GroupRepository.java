package com.experis.experisconnect.repositories;

import com.experis.experisconnect.models.Groups;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface GroupRepository extends JpaRepository<Groups, Integer> {
    @Query(value = "SELECT * FROM groups WHERE ((is_private = false) OR (is_private = true AND groups.id IN (SELECT groups_id FROM group_user WHERE users_id = ?1))) AND (lower(name) like (%?2%) or (lower(description) like (%?2%))) limit ?3 offset ?4", nativeQuery = true)
    Set<Groups> findGroupsByNameWithLimitOffset(String userId, String search, int limit, int offset);

    @Query(value = "SELECT * FROM groups WHERE id IN (SELECT groups_id FROM group_user WHERE users_id = ?1) ORDER BY name ASC", nativeQuery = true)
    Set<Groups> findGroupsAUserIsIn(String userId);
}
