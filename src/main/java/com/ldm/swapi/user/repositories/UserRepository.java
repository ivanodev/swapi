package com.ldm.swapi.user.repositories;

import com.ldm.swapi.user.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    boolean existsByLogin(String login);
    boolean existsById(UUID userId);

    User findByLogin(String login);

    @Modifying
    @Query("update User u set u.password = :password where u.id = :userId")
    void updatePassword(UUID userId, String password);
}
