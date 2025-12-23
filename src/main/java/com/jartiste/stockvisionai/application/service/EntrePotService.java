package com.jartiste.stockvisionai.application.service;

import com.jartiste.stockvisionai.presentation.dto.request.EntrePotRequest;
import com.jartiste.stockvisionai.presentation.dto.response.EntrePotResponse;

import java.util.List;

public interface EntrePotService {
    EntrePotResponse createEntrepot(EntrePotRequest request);
    List<EntrePotResponse> getAllEntrepot();
    EntrePotResponse findOneEntrepot(String id);
    EntrePotResponse updateEntrepot(String id, EntrePotRequest request);
    void deleteEntrepot(String id);
}
