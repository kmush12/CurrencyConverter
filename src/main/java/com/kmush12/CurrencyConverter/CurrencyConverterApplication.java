package com.kmush12.CurrencyConverter;

import com.kmush12.CurrencyConverter.converter.CurrencyConverterService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableFeignClients
@EnableScheduling
public class CurrencyConverterApplication implements CommandLineRunner {

    private final CurrencyConverterService service;

    public CurrencyConverterApplication(CurrencyConverterService service) {
        this.service = service;
    }

    public static void main(String[] args) {
        SpringApplication.run(CurrencyConverterApplication.class, args);
    }

    @Override
    public void run(String... args) {
        service.insertExchangeRates();
    }
}
