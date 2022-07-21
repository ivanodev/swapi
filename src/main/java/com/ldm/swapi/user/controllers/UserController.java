package com.ldm.swapi.user.controllers;

import com.ldm.swapi.user.dtos.ChangePasswordDto;
import com.ldm.swapi.user.dtos.UserDto;
import com.ldm.swapi.user.entities.User;
import com.ldm.swapi.user.factories.UserServiceFactory;
import com.ldm.swapi.user.services.ChangePasswordUserService;
import com.ldm.swapi.user.services.CreateUserService;
import com.ldm.swapi.user.services.LoaderUserService;
import com.ldm.swapi.user.services.UpdateUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/swapi/users")
public class UserController {

    @Autowired
    private UserServiceFactory userServiceFactory;

    @PostMapping
    public ResponseEntity<Object> createUser(@RequestBody UserDto userDto) throws Exception {

        ResponseEntity<Object> result;
        CreateUserService createUserService = this.userServiceFactory.createCreateUserService();

        try {

            UUID userId = createUserService.execute(userDto);
            result = ResponseEntity.status(HttpStatus.CREATED).body(userId);
        } catch (Exception e) {

            result = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

        return result;
    }

    @PutMapping
    public ResponseEntity<Object> updateUser(@RequestBody UserDto userDto) throws Exception {

        ResponseEntity<Object> result;
        UpdateUserService updateUserService = this.userServiceFactory.createUpdateUserService();

        try {

            UUID userId = updateUserService.execute(userDto);
            result = ResponseEntity.status(HttpStatus.OK).body(userId);
        } catch (Exception e) {

            result = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

        return result;
    }

    @PatchMapping
    public void changePassword(@RequestBody ChangePasswordDto changePasswordDto) throws Exception {

        try {

            ChangePasswordUserService changePasswordUserService = this.userServiceFactory.createChangePasswordUserService();
            changePasswordUserService.execute(changePasswordDto);
        } catch (Exception e) {

            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<Object> getAll(
            @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {

        ResponseEntity<Object> result;

        try {

            LoaderUserService loaderUserService = this.userServiceFactory.createLoaderUserService();
            result = ResponseEntity.status(HttpStatus.OK).body(loaderUserService.getAll(pageable));
        } catch (Exception e) {

            result = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

        return result;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Object> getById(@PathVariable(value = "userId") UUID userId) {

        Optional<User> userOptional;

        try {

            LoaderUserService loaderUserService = this.userServiceFactory.createLoaderUserService();
            userOptional = loaderUserService.getById(userId);
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

        if (Boolean.TRUE.equals(userOptional.isEmpty())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }

        return ResponseEntity.status(HttpStatus.OK).body(userOptional.get());
    }
}
