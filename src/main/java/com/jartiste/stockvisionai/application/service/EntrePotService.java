package com.jartiste.stockvisionai.application.service;

import com.jartiste.stockvisionai.domain.entity.Entrepot;
import com.jartiste.stockvisionai.presentation.dto.request.EntrePotRequest;
import com.jartiste.stockvisionai.presentation.dto.response.EntrePotResponse;

import java.util.List;

public interface EntrePotService {
    public EntrePotResponse createEntrepot(EntrePotRequest request);
    public List<EntrePotResponse> getAllEntrepot();
    public EntrePotResponse findOneEntrepot(String id);
    public EntrePotResponse updateEntrepot(String id);
    public void deleteEntrepot(String id);
}
