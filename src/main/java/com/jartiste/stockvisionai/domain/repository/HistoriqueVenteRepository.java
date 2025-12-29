package com.jartiste.stockvisionai.domain.repository;
import com.jartiste.stockvisionai.domain.entity.HistoriqueVente;
import java.util.List;
import java.util.Optional;
public interface HistoriqueVenteRepository {
    HistoriqueVente save(HistoriqueVente historiqueVente);
    Optional<HistoriqueVente> findById(String id);
    List<HistoriqueVente> findAll();
    List<HistoriqueVente> findByEntrepotId(String entrepotId);
    void deleteById(String id);
}
