package com.jartiste.stockvisionai.infrastructure.persistence;

import com.jartiste.stockvisionai.domain.entity.Prevision;
import com.jartiste.stockvisionai.domain.repository.PrevisionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class previsionRepositoryImpl implements PrevisionRepository {

 private final SpringDataPrevisionRepository repo;
    @Override
    public Prevision save(Prevision p) {
        return repo.save(p);
    }

    @Override
    public List<Prevision> findAll() {
        return repo.findAll();
    }

    @Override
    public List<Prevision> findByEntrepotId(String EntrepotId) {
        return repo.findByEntrepotId(EntrepotId);
    }
}
