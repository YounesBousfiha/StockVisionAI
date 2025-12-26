package com.jartiste.stockvisionai.infrastructure.persistence;
import com.jartiste.stockvisionai.domain.entity.HistoriqueVente;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface SpringDataHistoriqueVenteRepository extends MongoRepository<HistoriqueVente, String> {
}
