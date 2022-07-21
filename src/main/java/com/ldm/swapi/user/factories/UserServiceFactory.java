package com.ldm.swapi.user.factories;

import com.ldm.swapi.user.services.ChangePasswordUserService;
import com.ldm.swapi.user.services.CreateUserService;
import com.ldm.swapi.user.services.LoaderUserService;
import com.ldm.swapi.user.services.UpdateUserService;
import org.springframework.stereotype.Component;

@Component
public interface UserServiceFactory {

    CreateUserService createCreateUserService();

    UpdateUserService createUpdateUserService();

    ChangePasswordUserService createChangePasswordUserService();

    LoaderUserService createLoaderUserService();
}
