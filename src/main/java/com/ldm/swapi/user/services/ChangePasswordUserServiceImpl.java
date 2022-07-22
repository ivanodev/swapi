package com.ldm.swapi.user.services;

import com.ldm.swapi.user.dtos.ChangePasswordDto;
import com.ldm.swapi.user.entities.User;
import com.ldm.swapi.user.repositories.UserRepository;
import com.ldm.swapi.user.utils.PasswordUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

@Component
public class ChangePasswordUserServiceImpl implements ChangePasswordUserService {

    private final UserRepository userRepository;

    public ChangePasswordUserServiceImpl(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    @Override
    public void execute(ChangePasswordDto changePasswordDto) throws Exception {

        Optional<User> userOptional = this.userRepository.findById(changePasswordDto.getUserId());

        if (userOptional.isEmpty()) {

            throw new Exception("Conflict: User cannot exists!");
        }

//        boolean userExists = this.userRepository.existsById(changePasswordDto.getUserId());
//        if (!userExists) {
//            throw new Exception("Conflict: User cannot exists!");
//        }

        String password = PasswordUtils.encrypt(changePasswordDto.getPassword());
        User user = userOptional.get();
        user.setPassword(password);
        user.setUpdatedAt(LocalDateTime.now(ZoneId.of("UTC")));
        this.userRepository.save(user);
//        this.userRepository.updatePassword(changePasswordDto.getUserId(), password);
    }
}
