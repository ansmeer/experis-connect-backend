package com.experis.experisconnect.repositories;

import com.experis.experisconnect.models.Groups;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface GroupRepository extends JpaRepository<Groups, Integer> {
    @Query(value = "select * from Groups t where (lower(t.name) like (%?1%) or (lower(t.description) like (%?1%))) limit ?2 offset ?3", nativeQuery = true)
    Set<Groups> findTopicsByNameWithLimitOffset(String search, int limit, int offset);
}
