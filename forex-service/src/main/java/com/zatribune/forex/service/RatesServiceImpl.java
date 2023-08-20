package com.zatribune.forex.service;


import com.zatribune.forex.client.ExchangeRateClient;
import com.zatribune.forex.db.entity.TradeRate;
import com.zatribune.forex.db.entity.TradeRateId;
import com.zatribune.forex.db.repository.TradeRateRepository;
import com.zatribune.forex.domain.TradeRateResponse;
import com.zatribune.forex.domain.mapper.TradeRateMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class RatesServiceImpl implements RatesService {

    private final TradeRateRepository repository;
    private final ExchangeRateClient client;
    private final TradeRateMapper mapper;

    @Override
    public int refresh() throws IOException {
        log.info("Refreshing Trade Rates via ExchangeRateClient...");
        List<TradeRate> list = client.getRates();
        repository.saveAll(list);
        return list.size();
    }

    @Override
    public List<TradeRateResponse> getAll() {
        return repository.findAll().stream().map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public TradeRateResponse convert(String source, String destination) {

        TradeRate tradeRate = repository.findById(new TradeRateId(source, destination))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No trade rate available for the given input."));

        return mapper.toResponse(tradeRate);
    }
}
