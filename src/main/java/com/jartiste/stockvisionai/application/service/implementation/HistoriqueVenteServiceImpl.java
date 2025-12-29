package com.jartiste.stockvisionai.application.service.implementation;
import com.jartiste.stockvisionai.application.mapper.HistoriqueVenteMapper;
import com.jartiste.stockvisionai.application.service.HistoriqueVenteService;
import com.jartiste.stockvisionai.domain.entity.HistoriqueVente;
import com.jartiste.stockvisionai.domain.exception.ResourceNotFoundException;
import com.jartiste.stockvisionai.domain.repository.HistoriqueVenteRepository;
import com.jartiste.stockvisionai.presentation.dto.request.HistoriqueVenteRequest;
import com.jartiste.stockvisionai.presentation.dto.response.HistoriqueVenteResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.Year;
import java.util.List;
@Service
@RequiredArgsConstructor
public class HistoriqueVenteServiceImpl implements HistoriqueVenteService {
    private final HistoriqueVenteRepository historiqueVenteRepository;
    private final HistoriqueVenteMapper historiqueVenteMapper;
    private static final String NOT_FOUND_MSG = "HistoriqueVente not found with id: ";
    @Override
    public HistoriqueVenteResponse createHistoriqueVente(HistoriqueVenteRequest request) {
        HistoriqueVente entity = historiqueVenteMapper.toEntity(request);
        // TODO: make sure to verify Stock & decrease the stock size
        LocalDate now = LocalDate.now();
        entity.setDayOfWeek(now.getDayOfWeek());
        entity.setMonth(now.getMonth());
        entity.setYear(Year.of(now.getYear()));
        HistoriqueVente saved = historiqueVenteRepository.save(entity);
        return historiqueVenteMapper.toResponse(saved);
    }
    @Override
    public HistoriqueVenteResponse findOneHistoriqueVente(String id) {
        HistoriqueVente entity = historiqueVenteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND_MSG + id));
        return historiqueVenteMapper.toResponse(entity);
    }
    @Override
    public List<HistoriqueVenteResponse> findAllHistoriqueVentes() {
        return historiqueVenteRepository.findAll().stream()
                .map(historiqueVenteMapper::toResponse)
                .toList();
    }

    @Override
    public List<HistoriqueVenteResponse> findByEntrepotId(String entrepotId) {
        return historiqueVenteRepository.findByEntrepotId(entrepotId).stream()
                .map(historiqueVenteMapper::toResponse)
                .toList();
    }
}
