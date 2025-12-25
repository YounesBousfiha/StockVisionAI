package com.jartiste.stockvisionai.application.service.implementation;


import com.jartiste.stockvisionai.application.mapper.EntrepotMapper;
import com.jartiste.stockvisionai.application.service.EntrePotService;
import com.jartiste.stockvisionai.domain.entity.Entrepot;
import com.jartiste.stockvisionai.domain.exception.AlreadyAssignedException;
import com.jartiste.stockvisionai.domain.exception.NonAssignedEntrepot;
import com.jartiste.stockvisionai.domain.exception.ResourceNotFoundException;
import com.jartiste.stockvisionai.domain.repository.EntrepotRepository;
import com.jartiste.stockvisionai.presentation.dto.request.EntrePotRequest;
import com.jartiste.stockvisionai.presentation.dto.request.EntrepotUpdateRequest;
import com.jartiste.stockvisionai.presentation.dto.response.EntrePotResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EntrepotServiceImpl implements EntrePotService {

    private final EntrepotRepository entrepotRepository;
    private final EntrepotMapper entrepotMapper;
    private static final String NOT_FOUND_MSG = "Entrepot not found with id: ";

    @Override
    public EntrePotResponse createEntrepot(EntrePotRequest request) {
        Entrepot entity = entrepotMapper.toEntity(request);

        entity.setCreationAt(LocalDateTime.now());
        entity.setUpdateAt(LocalDateTime.now());

        Entrepot saved = entrepotRepository.save(entity);

        return entrepotMapper.toResponse(saved);
    }

    @Override
    public List<EntrePotResponse> getAllEntrepot() {
        return entrepotRepository.findAll().stream()
                .map(entrepotMapper::toResponse)
                .toList();
    }

    @Override
    public EntrePotResponse findOneEntrepot(String id) {
        Entrepot entity = entrepotRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND_MSG + id));
        return entrepotMapper.toResponse(entity);
    }

    @Override
    public List<EntrePotResponse> findByGestionnaireId(String gestionnaireId) {
        return entrepotRepository.findByGestionnaireId(gestionnaireId).stream()
                .map(entrepotMapper::toResponse)
                .toList();
    }

    @Override
    public EntrePotResponse updateEntrepot(String id, @Valid EntrepotUpdateRequest request) {
        Entrepot entity = entrepotRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND_MSG + id));

        entrepotMapper.updateEntrepotFromDTO(request, entity);
        entity.setUpdateAt(LocalDateTime.now());
        Entrepot saved = entrepotRepository.save(entity);
        return entrepotMapper.toResponse(saved);
    }

    @Override
    public void deleteEntrepot(String id) {
        entrepotRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND_MSG + id));
        entrepotRepository.deleteById(id);
    }

    @Override
    public EntrePotResponse assign(String entrepotId, String gestionnaireId) {
        Entrepot entrepot = this.entrepotRepository.findById(entrepotId)
                .orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND_MSG + entrepotId));

        List<Entrepot> entrepots = entrepotRepository.findByGestionnaireId(gestionnaireId);

        if(!entrepots.isEmpty()) {
            throw new AlreadyAssignedException("This User is Already Assigned to another Entrepot");
        }

        if(entrepot.getGestionnaireId() != null) {
            throw new AlreadyAssignedException("This Entrepot is Already Assigned");
        }

        entrepot.setGestionnaireId(gestionnaireId);
        entrepot.setUpdateAt(LocalDateTime.now());

        return entrepotMapper.toResponse(entrepotRepository.save(entrepot));
    }

    @Override
    public EntrePotResponse deassign(String entrepotId) {
        Entrepot entrepot = this.entrepotRepository.findById(entrepotId)
                .orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND_MSG + entrepotId));

        if(entrepot.getGestionnaireId() == null) {
            throw new NonAssignedEntrepot("This Entrepot is Already Unassigned");
        }

        entrepot.setGestionnaireId(null);

        entrepot.setUpdateAt(LocalDateTime.now());

        return entrepotMapper.toResponse(entrepotRepository.save(entrepot));

    }

}
