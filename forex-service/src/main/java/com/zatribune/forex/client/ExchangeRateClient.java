package com.zatribune.forex.client;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.zatribune.forex.db.entity.TradeRate;
import com.zatribune.forex.db.entity.TradeRateId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Component
public class ExchangeRateClient {


    private final ObjectMapper objectMapper;
    private final WebClient webClient;

    public List<TradeRate> getRates() throws IOException {
        List<TradeRate> rows = new ArrayList<>();
        ObjectNode result = webClient
                .get().uri("/latest")
                .exchangeToMono(clientResponse -> clientResponse.bodyToMono(ObjectNode.class))
                .onErrorResume(throwable -> {
                    throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,throwable.getMessage(),throwable);
                })
                .block();
        log.info("result exists:{}", result != null);
        if (result != null) {
            String base = result.get("base").asText();
            Map<String, Double> map = objectMapper.readValue(result.get("rates").traverse(), new TypeReference<>() {
            });
            rows = map.entrySet().stream().map(entry -> TradeRate.builder()
                    .id(new TradeRateId(base, entry.getKey()))
                    .rate(entry.getValue())
                    .build()).collect(Collectors.toList());
        }
        return rows;
    }


}
