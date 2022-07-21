package com.ldm.swapi.user.services;

import com.ldm.swapi.user.entities.User;
import com.ldm.swapi.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class LoaderUserServiceImpl implements LoaderUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Page<User> getAll(Pageable pageable) {

        return this.userRepository.findAll(pageable);
    }

    @Override
    public Optional<User> getById(UUID userId) {
        Optional<User> userOptional = this.userRepository.findById(userId);
        return userOptional;
    }
}
