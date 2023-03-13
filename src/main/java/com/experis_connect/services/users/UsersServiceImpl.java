package com.experis_connect.services.users;

import com.experis_connect.exceptions.UserNotFoundException;
import com.experis_connect.models.Users;
import com.experis_connect.repositories.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UsersServiceImpl implements UsersService{

    private final UsersRepository usersRepository;

    public UsersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public Users findById(String id) {
        return usersRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public Collection<Users> findAll() {
        return usersRepository.findAll();
    }

    @Override
    public Users add(Users entity) {
        return usersRepository.save(entity);
    }

    @Override
    public Users update(Users entity) {
        return usersRepository.save(entity);
    }

    @Override
    public void deleteById(String id) {
        usersRepository.deleteById(id);
    }

    @Override
    public boolean exists(String id) {
        return usersRepository.existsById(id);
    }
}
