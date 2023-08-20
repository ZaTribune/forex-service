package com.zatribune.forex.domain;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TradeRateResponse {
    private String source;
    private String destination;
    private double rate;
}
