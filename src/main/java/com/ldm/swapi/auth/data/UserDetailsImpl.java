package com.ldm.swapi.auth.data;

import com.ldm.swapi.user.entities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Collection;

public class UserDetailsImpl implements UserDetails {

    private final User user;

    public UserDetailsImpl(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }

    @Override
    public String getPassword() {
        String password = "";

        if (!ObjectUtils.isEmpty(this.user)) {
            password =  user.getPassword();
        }

        return password;
    }

    @Override
    public String getUsername() {
        String login = "";

        if (!ObjectUtils.isEmpty(this.user)) {
            login =  user.getLogin();
        }

        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        Boolean isActive = false;

        if (!ObjectUtils.isEmpty(this.user)) {
            isActive =  user.getIsActive();
        }

        return isActive;
    }
}
