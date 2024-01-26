package com.application.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExchangeRateResponse {
    private Map<String, Double> conversion_rates;
    private String base_code;
    private String date;
}
