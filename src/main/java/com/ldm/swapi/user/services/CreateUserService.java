package com.ldm.swapi.user.services;

import com.ldm.swapi.user.dtos.UserDto;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface CreateUserService {

    UUID execute(UserDto userDto) throws Exception;
}
