package com.ldm.swapi.user.services;

import com.ldm.swapi.user.dtos.UserDto;
import com.ldm.swapi.user.entities.User;
import com.ldm.swapi.user.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

@Component
public class UpdateUserServiceImpl implements UpdateUserService {

    private final UserRepository userRepository;

    public UpdateUserServiceImpl(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    @Override
    public UUID execute(UserDto userDto) throws Exception {

        User userFound = this.userRepository.findByLogin(userDto.getLogin());

        if (ObjectUtils.isEmpty(userFound)) {

            throw new Exception("Conflict: User cannot exists!");
        }

        if ( userDto.getId() != userFound.getId()) {

            throw new Exception("Conflict: User name already exists for another user!");
        }

        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        user.setPassword(userFound.getPassword());
        user.setUpdatedAt(LocalDateTime.now(ZoneId.of("UTC")));

        User userCreated = this.userRepository.save(user);
        return userCreated.getId();
    }
}
