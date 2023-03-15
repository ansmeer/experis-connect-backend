package com.experis_connect.services.group;

import com.experis_connect.exceptions.GroupNotFoundException;
import com.experis_connect.models.Groups;
import com.experis_connect.repositories.GroupRepository;
import com.experis_connect.repositories.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;

@Service
public class GroupServiceImpl implements GroupService {
    private final GroupRepository groupRepository;
    private final UsersRepository usersRepository;

    public GroupServiceImpl(GroupRepository groupRepository, UsersRepository usersRepository) {
        this.groupRepository = groupRepository;
        this.usersRepository = usersRepository;
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

    @Override
    public Set<Groups> searchResultsWithLimitOffset(String name, int page, int size) {
        return groupRepository.findTopicsByNameWithLimitOffset(name, size, page);
    }

    @Override
    public Groups addUserToGroup(String userId, int groupId) {
        Groups group = groupRepository.findById(groupId).orElseThrow(() -> new GroupNotFoundException(groupId));
        group.getUsers().add(usersRepository.findById(userId).get());

        return groupRepository.save(group);
    }
}
