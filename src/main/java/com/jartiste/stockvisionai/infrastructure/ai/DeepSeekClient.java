package com.jartiste.stockvisionai.infrastructure.ai;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.beans.factory.annotation.Value;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class DeepSeekClient {
  private final WebClient webClient;
    private final String model;
  public DeepSeekClient(WebClient.Builder webClientBuilder,
                        @Value("${spring.ai.deepseek.api-key}") String apiKey,
                        @Value("${spring.ai.deepseek.base-url}") String apiUrl,
                        @Value("${spring.ai.deepseek.model}") String model){
      this.model =model;
      this.webClient = webClientBuilder
              .baseUrl(apiUrl)
              .defaultHeader("Authorization", "Bearer " + apiKey)
              .defaultHeader("Content-Type", "application/json")
              .build();

  }

  public String generate (String prompt){

      var requestBody = Map.of(
              "model", model,
              "stream", false,
              "temperature", 0.1,
              "messages", List.of(Map.of("role", "user", "content", prompt)),
              "response_format", Map.of("type", "json_object")
      );

      return webClient.post()
              .bodyValue(requestBody)
              .retrieve()
              .bodyToMono(JsonNode.class)
              .map(this::parseResponse)
              .onErrorResume(e -> {
                  log.error("DeepSeek API Error: {}", e.getMessage());
                  return Mono.just("{}");
              })
              .block();



  }
    private String parseResponse(JsonNode response) {
        if (response != null && response.has("choices")) {
            return response.path("choices").get(0).path("message").path("content").asText();
        }
        return "{}";
    }
}
