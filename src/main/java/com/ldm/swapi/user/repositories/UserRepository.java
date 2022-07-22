package com.ldm.swapi.user.repositories;

import com.ldm.swapi.user.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByLogin(String login);

    boolean existsById(Long userId);

    User findByLogin(String login);

//    @Modifying
//    @Query("update Users set password = :password where id = :userId")
//    void update_Pass_word(@Param("userId") Long userId, @Param("password") String password);

}
