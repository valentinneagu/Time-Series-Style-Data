package com.application.backend.controller;

import com.application.backend.model.ExchangeRateResponse;
import com.application.backend.service.ExchangeRateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.io.IOException;
import java.lang.InterruptedException;


@RestController
@RequestMapping("/api/exchange-rates")
@CrossOrigin(origins = "http://localhost:8081")
public class ExchangeRateController {

    private final ExchangeRateService exchangeRateService;

    public ExchangeRateController(ExchangeRateService exchangeRateService) {
        this.exchangeRateService = exchangeRateService;
    }

    @GetMapping("/latest")
    public ResponseEntity<?> getLatestRates() {
        try {
            ExchangeRateResponse response = exchangeRateService.getLatestRates();
            return ResponseEntity.ok(response);
        } catch (IOException | InterruptedException e) {
            return ResponseEntity.status(500).body("Error fetching exchange rates: " + e.getMessage());
        }
    }
}
