package com.jartiste.stockvisionai.infrastructure.persistence;

import com.jartiste.stockvisionai.domain.entity.Entrepot;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataEntrepotRepository extends MongoRepository<Entrepot, String> {
}
