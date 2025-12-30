package com.jartiste.stockvisionai.domain.repository;

import com.jartiste.stockvisionai.domain.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends MongoRepository<User, UUID> {
    Optional<User> findByEmail(String email);
    List<User> findByEntrepotId(String entrepotId);
    Optional<User> findById(String id);
}
