package com.ldm.swapi.user.services;

import com.ldm.swapi.user.dtos.UserDto;
import com.ldm.swapi.user.entities.User;
import com.ldm.swapi.user.repositories.UserRepository;
import com.ldm.swapi.user.utils.PasswordUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

@Component
public class CreateUserServiceImpl implements CreateUserService {

    private final UserRepository userRepository;

    public CreateUserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UUID execute(UserDto userDto) throws Exception {

        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        user.setCreatedAt(LocalDateTime.now(ZoneId.of("UTC")));

        boolean userNameExists = this.userRepository.existsByLogin(userDto.getLogin());

        if (userNameExists) {

            throw new Exception("Conflict: User name already exists!");
        }

        user.setPassword(PasswordUtils.encrypt(userDto.getPassword()));

        User userCreated = this.userRepository.save(user);
        return userCreated.getId();
    }
}
