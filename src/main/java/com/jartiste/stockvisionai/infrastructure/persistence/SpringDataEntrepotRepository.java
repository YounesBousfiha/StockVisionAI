package com.jartiste.stockvisionai.infrastructure.persistence;

import com.jartiste.stockvisionai.domain.entity.Entrepot;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpringDataEntrepotRepository extends MongoRepository<Entrepot, String> {
    List<Entrepot> findByGestionnaireId(String gestionnaireId);
}
