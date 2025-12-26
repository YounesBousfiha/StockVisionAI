package com.jartiste.stockvisionai.domain.repository;

import com.jartiste.stockvisionai.domain.entity.Entrepot;

import java.util.List;
import java.util.Optional;

public interface EntrepotRepository {
    Entrepot save(Entrepot entrepot);
    Optional<Entrepot> findById(String id);
    List<Entrepot> findAll();
    List<Entrepot> findByGestionnaireId(String gestionnaireId);
    void deleteById(String id);
}
