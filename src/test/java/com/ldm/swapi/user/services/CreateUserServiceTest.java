package com.ldm.swapi.user.services;

import com.ldm.swapi.user.dtos.UserDto;
import com.ldm.swapi.user.entities.User;
import com.ldm.swapi.user.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.BeanUtils;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DisplayName("User creation service test.")
public class CreateUserServiceTest {

    private UserRepository userRepository;
    private CreateUserServiceImpl createUserService;

    private User user;
    private UserDto userDto;

    @BeforeEach
    public void setup() {

        userDto = new UserDto();
        userDto.setId(10L);
        userDto.setFirstName("Luis");
        userDto.setLastName("Silva");
        userDto.setPassword("Ldm##2022");
        userDto.setLogin("ivano");
        userDto.setIsActive(true);

        user = new User();
        BeanUtils.copyProperties(userDto, user);

        userRepository = Mockito.mock(UserRepository.class);
        createUserService = new CreateUserServiceImpl(userRepository);
    }

    @Test
    @DisplayName("User creation executed successfully.")
    public void shouldReturnSuccess_WhenCreateUser() throws Exception {

        Mockito.when(userRepository.existsByLogin(user.getLogin())).thenReturn(false);
        Mockito.when(userRepository.save(Mockito.any())).thenReturn(user);

        Long userId = this.createUserService.execute(userDto);

        Assertions.assertNotNull(userId);
        Assertions.assertEquals(10L, user.getId());

        var numberInvocationsMethod = 1;

        Mockito.verify(userRepository, Mockito.times(numberInvocationsMethod)).existsByLogin(Mockito.any());
        Mockito.verify(userRepository, Mockito.times(numberInvocationsMethod)).save(Mockito.any());
    }

    @Test
    @DisplayName("Exception thrown when trying to create user with existing login.")
    public void shouldReturnExceptionLoginExists_WhenCreateUser() {

        Mockito.when(userRepository.existsByLogin(Mockito.anyString())).thenReturn(true);
        String exceptionMessage = "Conflict: User name already exists!";

        try {

            Long userId = this.createUserService.execute(userDto);
        } catch (Exception e) {

            Assertions.assertEquals(exceptionMessage, e.getMessage());
        }

        var numberInvocationsMethod = 1;

        Mockito.verify(userRepository, Mockito.times(numberInvocationsMethod)).existsByLogin(Mockito.any());
        Mockito.verify(userRepository, Mockito.never()).save(Mockito.any());
    }
}
