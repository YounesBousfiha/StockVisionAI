package com.jartiste.stockvisionai.infrastructure.persistence;

import com.jartiste.stockvisionai.domain.entity.Entrepot;
import com.jartiste.stockvisionai.domain.repository.EntrepotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@Component
@RequiredArgsConstructor
public class EntrepotRepositoryImpl  implements EntrepotRepository {

    private final SpringDataEntrepotRepository jpaRepository;

    @Override
    public Entrepot save(Entrepot entrepot) {
        return jpaRepository.save(entrepot);
    }

    @Override
    public Optional<Entrepot> findById(String id) {
        return jpaRepository.findById(id);
    }

    @Override
    public List<Entrepot> findAll() {
        return jpaRepository.findAll();
    }

    @Override
    public void deleteById(String id) {
        jpaRepository.deleteById(id);
    }
}
