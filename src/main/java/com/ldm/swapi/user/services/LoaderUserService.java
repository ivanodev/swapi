package com.ldm.swapi.user.services;

import com.ldm.swapi.user.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public interface LoaderUserService {

    Page<User> getAll(Pageable pageable);
    Optional<User> getById(UUID userId);
}
