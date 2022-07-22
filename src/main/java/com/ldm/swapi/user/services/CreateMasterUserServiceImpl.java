package com.ldm.swapi.user.services;

import com.ldm.swapi.user.dtos.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateMasterUserServiceImpl implements CreateMasterUserService {

    @Autowired
    private CreateUserService createUserService;

    @Override
    public void execute() throws Exception {

        UserDto userDto = new UserDto();
        userDto.setFirstName("LDM");
        userDto.setLastName("Master");
        userDto.setLogin("master");
        userDto.setIsActive(true);
        userDto.setPassword("Ldm##2022");

        this.createUserService.execute(userDto);
    }
}
