package com.zatribune.forex.controller;


import com.zatribune.forex.domain.ForexRequest;
import com.zatribune.forex.domain.GenericResponse;
import com.zatribune.forex.domain.TradeRateResponse;
import com.zatribune.forex.service.RatesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;


@Slf4j
@RequiredArgsConstructor
@RequestMapping("/rates")
@RestController
public class CurrencyController {

    private final RatesService ratesService;


    @GetMapping
    public GenericResponse<List<TradeRateResponse>> getAll() {
        log.info("Getting all trade rates.");
        return GenericResponse.<List<TradeRateResponse>>builder()
                .code(200)
                .message("Successful")
                .body(ratesService.getAll())
                .build();
    }

    @GetMapping("/refresh")
    public Integer refresh() throws IOException {
        log.info("Refresh Trade Rates");
        return ratesService.refresh();
    }



    @PostMapping("/convert")
    public GenericResponse<TradeRateResponse> convert(@RequestBody @Valid ForexRequest request) {
        log.info("convert: {}", request);

        return GenericResponse.<TradeRateResponse>builder()
                .code(200)
                .message("Successful")
                .body(ratesService.convert(request.getSource(),request.getDestination()))
                .build();
    }

}
