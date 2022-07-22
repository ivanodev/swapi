package com.ldm.swapi.user.services;

import com.ldm.swapi.user.entities.User;
import com.ldm.swapi.user.repositories.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class LoaderUserServiceImpl implements LoaderUserService {

    private final UserRepository userRepository;

    public LoaderUserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Page<User> getAll(Pageable pageable) {

        return this.userRepository.findAll(pageable);
    }

    @Override
    public Optional<User> getById(Long userId) {
        Optional<User> userOptional = this.userRepository.findById(userId);
        return userOptional;
    }
}
