package com.jartiste.stockvisionai.application.service;

import com.jartiste.stockvisionai.presentation.dto.request.EntrePotRequest;
import com.jartiste.stockvisionai.presentation.dto.request.EntrepotUpdateRequest;
import com.jartiste.stockvisionai.presentation.dto.response.EntrePotResponse;
import jakarta.validation.Valid;

import java.util.List;

public interface EntrePotService {
    EntrePotResponse createEntrepot(EntrePotRequest request);
    List<EntrePotResponse> getAllEntrepot();
    EntrePotResponse findOneEntrepot(String id);
    EntrePotResponse updateEntrepot(String id, @Valid EntrepotUpdateRequest request);
    void deleteEntrepot(String id);

}
