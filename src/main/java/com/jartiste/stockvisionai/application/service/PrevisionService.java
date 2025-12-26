package com.jartiste.stockvisionai.application.service;


import com.jartiste.stockvisionai.presentation.dto.request.PrevisionRequest;
import com.jartiste.stockvisionai.presentation.dto.response.PrevisionResponse;

import java.util.List;

public interface PrevisionService {
    PrevisionResponse createPrevision(PrevisionRequest request);
    List<PrevisionResponse> findAllPrevision();
}
