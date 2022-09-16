package com.kmush12.CurrencyConverter.exchange;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExchangeRateProvider {

    private final ExternalExchangeRateClient externalExchangeRateClient;

    public ExchangeRateProvider(ExternalExchangeRateClient externalExchangeRateClient) {
        this.externalExchangeRateClient = externalExchangeRateClient;
    }

    public ExchangeRate getExchangeRate(String originCurrency, String destinationCurrency) {
        return new ExchangeRate(externalExchangeRateClient.getExternalRate(originCurrency, destinationCurrency).base(), externalExchangeRateClient.getExternalRate(originCurrency, destinationCurrency).rates());
    }

    public List<CurrencyInfo> getCurrencyList() {
        return externalExchangeRateClient.getExternalCurrencyList().stream()
                .map(externalExchangeRateClient -> new CurrencyInfo(externalExchangeRateClient.description(), externalExchangeRateClient.code()))
                .toList();
    }
}
