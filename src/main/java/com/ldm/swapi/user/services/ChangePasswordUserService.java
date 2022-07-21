package com.ldm.swapi.user.services;

import com.ldm.swapi.user.dtos.ChangePasswordDto;
import org.springframework.stereotype.Service;

@Service
public interface ChangePasswordUserService {

    void execute(ChangePasswordDto changePasswordDto) throws Exception;
}
