package com.jartiste.stockvisionai.domain.repository;

import com.jartiste.stockvisionai.domain.entity.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findByEmail(String email);
}
