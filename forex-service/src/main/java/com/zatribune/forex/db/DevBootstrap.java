package com.zatribune.forex.db;


import com.zatribune.forex.client.ExchangeRateClient;
import com.zatribune.forex.db.repository.TradeRateRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class DevBootstrap implements CommandLineRunner {

    private final TradeRateRepository repository;
    private final ExchangeRateClient client;

    @Override
    public void run(String... args) throws Exception {
        log.info("Bootstrapping the application.");
        if (repository.count() == 0) {
            int count = repository.saveAll(client.getRates()).size();
            log.info("Data loaded successfully: {} rows.", count);
        }

    }
}
