package com.jartiste.stockvisionai.infrastructure.service;

import com.jartiste.stockvisionai.domain.entity.HistoriqueVente;
import com.jartiste.stockvisionai.domain.entity.Stock;
import com.jartiste.stockvisionai.domain.repository.HistoriqueVenteRepository;
import com.jartiste.stockvisionai.domain.repository.StockRepository;
import com.jartiste.stockvisionai.infrastructure.ai.DeepSeekClient;
import com.jartiste.stockvisionai.infrastructure.ai.PredictionPromptBuilder;
import com.jartiste.stockvisionai.presentation.dto.response.PredictionResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.IntSummaryStatistics;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class PredictionService {

    private final HistoriqueVenteRepository historiqueRepo;
    private final StockRepository stockRepo;
    private final DeepSeekClient deepSeekClient;
    private final PredictionPromptBuilder promptBuilder;

    public PredictionResponse predireStock(String entrepotId, String productId) {
        Stock stock = stockRepo.findByEntrepotIdAndProductId(entrepotId, productId);
        if (stock == null) {
            log.warn("Stock not found for Product: {} in Entrepot: {}", productId, entrepotId);
            throw new RuntimeException("Stock introuvable");
        }

        LocalDate oneMonthAgo = LocalDate.now().minusMonths(1);
        List<HistoriqueVente> history = historiqueRepo.findByEntrepotIdAndProductId(entrepotId, productId);

        List<HistoriqueVente> filteredHistory = history.stream()
                .filter(h -> h.getCreateAt() != null && h.getCreateAt().isAfter(oneMonthAgo))
                .toList();

        IntSummaryStatistics stats = filteredHistory.stream()
                .mapToInt(HistoriqueVente::getQuantity)
                .summaryStatistics();

        long totalUnits = stats.getSum();
        long txCount = stats.getCount();
        double dailyAvg = (txCount > 0) ? (double) totalUnits / 30.0 : 0.0;

        String promptJson = promptBuilder.buildPrompt(stock, filteredHistory, totalUnits, txCount, dailyAvg);

        log.debug("Generated Prompt: {}", promptJson);

        String aiRawResponse = deepSeekClient.generate(promptJson);

        return promptBuilder.parseResponse(aiRawResponse, entrepotId, productId);
    }
}