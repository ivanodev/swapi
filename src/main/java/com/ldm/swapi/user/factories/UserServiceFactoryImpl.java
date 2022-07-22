package com.ldm.swapi.user.factories;

import com.ldm.swapi.user.services.ChangePasswordUserService;
import com.ldm.swapi.user.services.CreateUserService;
import com.ldm.swapi.user.services.LoaderUserService;
import com.ldm.swapi.user.services.UpdateUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServiceFactoryImpl implements UserServiceFactory {

    @Autowired
    private CreateUserService createUserService;

    @Autowired
    private UpdateUserService updateUserService;

    @Autowired
    private ChangePasswordUserService changePasswordUserService;

    @Autowired
    private LoaderUserService loaderUserService;

    @Override
    public CreateUserService createCreateUserService() {
        return this.createUserService;
    }

    @Override
    public UpdateUserService createUpdateUserService() {
        return this.updateUserService;
    }

    @Override
    public ChangePasswordUserService createChangePasswordUserService() {
        return this.changePasswordUserService;
    }

    @Override
    public LoaderUserService createLoaderUserService() {
        return this.loaderUserService;
    }
}
