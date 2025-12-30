package com.jartiste.stockvisionai.infrastructure.persistence;
import com.jartiste.stockvisionai.domain.entity.HistoriqueVente;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpringDataHistoriqueVenteRepository extends MongoRepository<HistoriqueVente, String> {
    List<HistoriqueVente> findByEntrepotId(String entrepotId);
    List<HistoriqueVente> findByEntrepotIdAndProductId(String entrepotId, String productId);
}
