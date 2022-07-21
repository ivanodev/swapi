package com.ldm.swapi.user.services;

import com.ldm.swapi.auth.data.UserDetailsImpl;
import com.ldm.swapi.user.entities.User;
import com.ldm.swapi.user.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = this.userRepository.findByLogin(username);

        if (ObjectUtils.isEmpty(user)) throw new UsernameNotFoundException(String.format("User %s no found.", username));

        return new UserDetailsImpl(user);
    }
}
