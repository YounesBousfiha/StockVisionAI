package com.jartiste.stockvisionai.application.service.implementation;

import com.jartiste.stockvisionai.application.mapper.PrevisionMapper;
import com.jartiste.stockvisionai.application.service.PrevisionService;
import com.jartiste.stockvisionai.domain.entity.Prevision;
import com.jartiste.stockvisionai.domain.entity.Product;
import com.jartiste.stockvisionai.domain.repository.PrevisionRepository;
import com.jartiste.stockvisionai.presentation.dto.request.PrevisionRequest;
import com.jartiste.stockvisionai.presentation.dto.response.PrevisionResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class PrevisionServiceImpl implements PrevisionService {
    private final PrevisionMapper previsionMapper ;
    private final PrevisionRepository previsionRepository ;


        @Override
        public PrevisionResponse createPrevision(PrevisionRequest request) {
            Prevision p = previsionMapper.toEntity(request);
            Prevision saved = previsionRepository.save(p);
            return previsionMapper.toResponse(saved);
        }

        @Override
        public List<PrevisionResponse> findAllPrevision() {
            return previsionRepository.findAll().stream()
                    .map(previsionMapper::toResponse)
                    .toList();
        }
}
