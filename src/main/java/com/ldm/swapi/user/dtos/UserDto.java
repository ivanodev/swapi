package com.ldm.swapi.user.dtos;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
public class UserDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private UUID id;
    private String firstName;
    private String lastName;
    private String login;
    private String password;
    private Boolean isActive;
}
