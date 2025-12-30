package com.jartiste.stockvisionai.application.service;
import com.jartiste.stockvisionai.presentation.dto.request.HistoriqueVenteRequest;
import com.jartiste.stockvisionai.presentation.dto.response.HistoriqueVenteResponse;
import java.util.List;
public interface HistoriqueVenteService {
    HistoriqueVenteResponse createHistoriqueVente(HistoriqueVenteRequest request,String entrepot_id);
    HistoriqueVenteResponse findOneHistoriqueVente(String id);
    List<HistoriqueVenteResponse> findAllHistoriqueVentes();
}
