package com.jartiste.stockvisionai.application.service.implementation;
import com.jartiste.stockvisionai.application.mapper.HistoriqueVenteMapper;
import com.jartiste.stockvisionai.application.service.HistoriqueVenteService;
import com.jartiste.stockvisionai.domain.entity.HistoriqueVente;
import com.jartiste.stockvisionai.domain.exception.ResourceNotFoundException;
import com.jartiste.stockvisionai.domain.repository.HistoriqueVenteRepository;
import com.jartiste.stockvisionai.infrastructure.service.SecurityUtils;
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
    private final SecurityUtils securityUtils;
    private static final String NOT_FOUND_MSG = "HistoriqueVente not found with id: ";

    @Override
    public HistoriqueVenteResponse createHistoriqueVente(
            HistoriqueVenteRequest request,
            String entrepot_id
    ) {
        if (request == null) {
            throw new IllegalArgumentException("La requête ne peut pas être nulle");
        }
        if (entrepot_id == null || entrepot_id.isBlank()) {
            throw new IllegalArgumentException("Entrepot ID est obligatoire");
        }


        if (!securityUtils.hasAccessToEntrepot(entrepot_id)) {
            throw new org.springframework.security.access.AccessDeniedException(
                    "Vous n'avez pas le droit d'effectuer cette action sur cet entrepôt."
            );
        }

        HistoriqueVente entity = historiqueVenteMapper.toEntity(request);


        LocalDate referenceDate = LocalDate.now().minusDays(1);

        entity.setCreateAt(referenceDate);
        entity.setDayOfWeek(referenceDate.getDayOfWeek());
        entity.setMonth(referenceDate.getMonth());
        entity.setYear(referenceDate.getYear());
        entity.setEntrepotId(entrepot_id);

        HistoriqueVente saved = historiqueVenteRepository.save(entity);
        return historiqueVenteMapper.toResponse(saved);
    }

    @Override
    public HistoriqueVenteResponse findOneHistoriqueVente(String id) {
        HistoriqueVente entity = historiqueVenteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND_MSG + id));
        if (!securityUtils.hasAccessToEntrepot(entity.getEntrepotId())) {
            throw new org.springframework.security.access.AccessDeniedException(
                    "Vous n'avez pas le droit d'effectuer cette action sur cet entrepôt."
            );
        }
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
