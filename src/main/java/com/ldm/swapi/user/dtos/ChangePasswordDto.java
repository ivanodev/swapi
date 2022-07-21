package com.ldm.swapi.user.dtos;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
public class ChangePasswordDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private UUID userId;
    private String password;
}
