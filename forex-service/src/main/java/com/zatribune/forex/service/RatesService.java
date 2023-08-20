package com.zatribune.forex.service;

import com.zatribune.forex.domain.TradeRateResponse;

import java.io.IOException;
import java.util.List;

public interface RatesService {


    int refresh() throws IOException;

    List<TradeRateResponse> getAll();

    TradeRateResponse convert(String source, String destination);
}
