package com.jartiste.stockvisionai.infrastructure.ai;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.jartiste.stockvisionai.domain.entity.HistoriqueVente;
import com.jartiste.stockvisionai.domain.entity.Stock;
import com.jartiste.stockvisionai.presentation.dto.response.PredictionResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class PredictionPromptBuilder {

    private final ObjectMapper objectMapper;

    public String buildPrompt(Stock stock, List<HistoriqueVente> history, long totalUnits, long txCount, double avg) {
        ObjectNode root = objectMapper.createObjectNode();
        int alertThreshold = (stock.getSeuilAlerte() != null) ? stock.getSeuilAlerte() : 0;
        int currentStock = stock.getQuantity();

        root.putObject("system")
                .put("role", "system")
                .put("content", "You are an expert Inventory AI. Explain your reasoning. Goal: Prevent stockouts with 20% safety margin. Return strictly JSON.");

        ObjectNode productNode = root.putObject("product_context");
        productNode.put("product_id", stock.getProductId());
        productNode.put("current_stock", currentStock);
        productNode.put("alert_threshold", alertThreshold);

        ObjectNode statsNode = root.putObject("sales_statistics_last_30_days");
        statsNode.put("total_units_sold", totalUnits);
        statsNode.put("total_transactions", txCount);
        statsNode.put("daily_average", avg);

        ArrayNode historyArr = root.putArray("daily_sales_history");
        history.forEach(h -> {
            if (h.getCreateAt() != null) {
                historyArr.addObject()
                        .put("date", h.getCreateAt().toString())
                        .put("quantity", h.getQuantity());
            }
        });

        addLogicInstructions(root, currentStock);

        return root.toString();
    }

    public PredictionResponse parseResponse(String rawJson, String entrepotId, String productId) {
        try {
            String cleanJson = rawJson.replaceAll("```json", "").replaceAll("```", "").trim();
            JsonNode root = objectMapper.readTree(cleanJson);

            return PredictionResponse.builder()
                    .productId(productId)
                    .entrepotId(entrepotId)
                    .datePrevision(LocalDate.now())
                    .quantitePrevue30Jours(root.path("quantite_prevue_30_jours").asInt())
                    .niveauConfiance(root.path("niveau_confiance").asInt())
                    .recommandation(root.path("recommandation").asText())
                    .build();
        } catch (JsonProcessingException e) {
            log.error("Failed to parse AI response. Raw: {}", rawJson, e);
            throw new RuntimeException("AI Parsing Error", e);
        }
    }

    private void addLogicInstructions(ObjectNode root, int currentStock) {
        ObjectNode task = root.putObject("task");
        task.put("objective", "Predict sales and recommend order quantity.");

        String logic = String.format(
                "1. Predict SALES. 2. REQUIRED = SALES * 1.2. " +
                        "3. IF (%d < REQUIRED) THEN 'Stock actuel (%d) critique vs Besoins (' + REQUIRED + '). RECOMMANDATION : Commander ' + (REQUIRED - %d) + ' unités'. " +
                        "ELSE 'Stock actuel (%d) suffisant (Couverture > 120%%).'",
                currentStock, currentStock, currentStock, currentStock
        );

        task.put("algorithm_steps", logic);

        ObjectNode schema = task.putObject("output_format")
                .put("type", "json")
                .putObject("required_schema");
        schema.put("quantite_prevue_30_jours", "Integer");
        schema.put("niveau_confiance", "Integer");
        schema.put("recommandation", "String (Use the template from algorithm_steps)");
    }
}