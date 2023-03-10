package com.experis_connect.repositories;

import com.experis_connect.models.Groups;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Groups, Integer> {
}
