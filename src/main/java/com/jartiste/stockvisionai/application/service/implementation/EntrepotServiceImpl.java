package com.jartiste.stockvisionai.application.service.implementation;


import com.jartiste.stockvisionai.application.service.EntrePotService;
import com.jartiste.stockvisionai.domain.entity.Entrepot;
import com.jartiste.stockvisionai.domain.repository.EntrepotRepository;
import com.jartiste.stockvisionai.presentation.dto.request.EntrePotRequest;
import com.jartiste.stockvisionai.presentation.dto.response.EntrePotResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EntrepotServiceImpl implements EntrePotService {

    private final EntrepotRepository entrepotRepository;

    @Override
    public EntrePotResponse createEntrepot(EntrePotRequest request) {
        return null;
    }

    @Override
    public List<EntrePotResponse>  getAllEntrepot() {
        return null;
    }

    @Override
    public EntrePotResponse findOneEntrepot(String id) {
        return null;
    }

    @Override
    public EntrePotResponse updateEntrepot(String id) {
        return null;
    }

    @Override
    public void deleteEntrepot(String id) {}

}
