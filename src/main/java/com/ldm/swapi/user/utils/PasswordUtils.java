package com.ldm.swapi.user.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtils {

    public static String encrypt(String password) {

        return new BCryptPasswordEncoder().encode(password);
    }

    public static Boolean matches(String password, String encryptPassword) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(password, encryptPassword);
    }
}
