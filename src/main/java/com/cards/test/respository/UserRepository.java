package com.cards.test.respository;

import com.cards.test.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT * FROM users WHERE email =?1", nativeQuery = true)
    Optional<User> findByEmail(String email);

    Optional<User> findUserById(Long id);
}
