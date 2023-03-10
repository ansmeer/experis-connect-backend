package com.experis_connect.services.group;

import com.experis_connect.exceptions.GroupNotFoundException;
import com.experis_connect.models.Groups;
import com.experis_connect.repositories.GroupRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class GroupServiceImpl implements GroupService {
    private final GroupRepository groupRepository;

    public GroupServiceImpl(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @Override
    public Groups findById(Integer id) {
        return groupRepository.findById(id).orElseThrow(() -> new GroupNotFoundException(id));
    }

    @Override
    public Collection<Groups> findAll() {
        return groupRepository.findAll();
    }

    @Override
    public Groups add(Groups entity) {
        return groupRepository.save(entity);
    }

    @Override
    public Groups update(Groups entity) {
        return groupRepository.save(entity);
    }

    @Override
    public void deleteById(Integer id) {
        groupRepository.deleteById(id);
    }

    @Override
    public boolean exists(Integer id) {
        return groupRepository.existsById(id);
    }
}
