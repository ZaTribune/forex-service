package com.zatribune.forex.domain;


import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ForexRequest {


    @NotBlank(message = "Source currency cannot be null or blank.")
    private String source;
    @NotBlank(message = "Destination currency cannot be null or blank.")
    private String destination;
}
