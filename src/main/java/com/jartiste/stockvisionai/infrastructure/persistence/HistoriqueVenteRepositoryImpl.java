package com.jartiste.stockvisionai.infrastructure.persistence;
import com.jartiste.stockvisionai.domain.entity.HistoriqueVente;
import com.jartiste.stockvisionai.domain.repository.HistoriqueVenteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;
@Component
@RequiredArgsConstructor
public class HistoriqueVenteRepositoryImpl implements HistoriqueVenteRepository {
    private final SpringDataHistoriqueVenteRepository repo;
    @Override
    public HistoriqueVente save(HistoriqueVente historiqueVente) {
        return repo.save(historiqueVente);
    }
    @Override
    public Optional<HistoriqueVente> findById(String id) {
        return repo.findById(id);
    }
    @Override
    public List<HistoriqueVente> findAll() {
        return repo.findAll();
    }
    @Override
    public List<HistoriqueVente> findByEntrepotId(String entrepotId) {
        return repo.findByEntrepotId(entrepotId);
    }
    @Override
    public void deleteById(String id) {
        repo.deleteById(id);
    }
}
