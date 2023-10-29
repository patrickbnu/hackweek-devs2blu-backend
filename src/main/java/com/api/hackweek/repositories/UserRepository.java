package com.api.hackweek.repositories;

import com.api.hackweek.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<UserDetails> findByLogin(String login);

    Optional<User> findByLoginIgnoreCase(String login);

    Optional<User> findByResetPasswordToken(String token);

    boolean existsByLogin(String login);
}
