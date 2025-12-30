package com.jartiste.stockvisionai.domain.repository;

import com.jartiste.stockvisionai.domain.entity.Prevision;
import com.jartiste.stockvisionai.domain.entity.Product;

import java.util.List;
import java.util.Optional;

public interface PrevisionRepository {
    Prevision save(Prevision p);
    List<Prevision> findAll();
}
