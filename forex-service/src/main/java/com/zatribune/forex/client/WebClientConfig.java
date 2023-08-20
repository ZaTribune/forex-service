package com.zatribune.forex.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;


@Slf4j
@Configuration
public class WebClientConfig {


    @Bean
   public WebClient webClient() {
        return WebClient
                .builder()
                .filter(errorHandler())
                .baseUrl("https://api.exchangerate.host")
                .build();
    }

    public static ExchangeFilterFunction errorHandler() {
        return ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
            HttpStatus status=clientResponse.statusCode();
            if (clientResponse.statusCode().is5xxServerError()) {
                return clientResponse.bodyToMono(String.class)
                        .flatMap(errorBody -> Mono.error(new ResponseStatusException(status,errorBody)));
            } else if (clientResponse.statusCode().is4xxClientError()) {
                return clientResponse.bodyToMono(String.class)
                        .flatMap(errorBody -> Mono.error(new ResponseStatusException(status,errorBody)));
            } else {
                return Mono.just(clientResponse);
            }
        });
    }
}
