package com.jartiste.stockvisionai.infrastructure.persistence;

import com.jartiste.stockvisionai.domain.entity.Prevision;
import com.jartiste.stockvisionai.domain.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpringDataPrevisionRepository extends MongoRepository<Prevision, String> {
    List<Prevision> findByEntrepotId(String EntrepotId);

}
