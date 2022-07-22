package com.ldm.swapi.user.services;

import com.ldm.swapi.user.dtos.UserDto;
import org.springframework.stereotype.Service;

@Service
public interface CreateUserService {

    Long execute(UserDto userDto) throws Exception;
}
