package com.ldm.swapi.user.services;

import com.ldm.swapi.user.entities.User;
import com.ldm.swapi.user.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@DisplayName("User loader service test.")
public class LoadUserServiceTest {

    UserRepository userRepository;
    LoaderUserService loaderUserService;
    Long userId;

    @BeforeEach
    public void setup() {

        userRepository = Mockito.mock(UserRepository.class);
        loaderUserService = new LoaderUserServiceImpl(userRepository);
        userId = 10L;

        User user = new User();
        user.setId(userId);
        user.setLogin("jucasilva");

        Optional<User> userOptional = Optional.of(user);

        Mockito.when(userRepository.findById(userId)).thenReturn(userOptional);
    }

    @Test
    @DisplayName("Find user by id executed successfully.")
    public void shouldReturnSuccess_WhenFindUserById() {

        Optional<User> userOptional = loaderUserService.getById(userId);

        Assertions.assertEquals(true, userOptional.isPresent());
        Assertions.assertEquals("jucasilva", userOptional.get().getLogin());

        var numberInvocationsMethod = 1;
        Mockito.verify(userRepository,
                Mockito.times(numberInvocationsMethod)
        ).findById(userId);
    }

}
