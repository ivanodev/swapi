package com.ldm.swapi.user.services;

import com.ldm.swapi.user.dtos.ChangePasswordDto;
import com.ldm.swapi.user.repositories.UserRepository;
import com.ldm.swapi.user.utils.PasswordUtils;
import org.springframework.stereotype.Component;

@Component
public class ChangePasswordUserServiceImpl implements ChangePasswordUserService {

    private final UserRepository userRepository;

    public ChangePasswordUserServiceImpl(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    @Override
    public void execute(ChangePasswordDto changePasswordDto) throws Exception {

        boolean userExists = this.userRepository.existsById(changePasswordDto.getUserId());

        if (!userExists) {

            throw new Exception("Conflict: User cannot exists!");
        }

        String password = PasswordUtils.encrypt(changePasswordDto.getPassword());
        this.userRepository.updatePassword(changePasswordDto.getUserId(), password);
    }
}
