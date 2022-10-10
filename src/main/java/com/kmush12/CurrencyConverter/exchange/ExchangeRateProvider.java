package com.kmush12.CurrencyConverter.exchange;

import org.springframework.stereotype.Component;

@Component
public class ExchangeRateProvider {

    private final ExternalExchangeRateClient externalExchangeRateClient;

    public ExchangeRateProvider(ExternalExchangeRateClient externalExchangeRateClient) {
        this.externalExchangeRateClient = externalExchangeRateClient;
    }

    public ExchangeRate getExchangeRate(String originCurrency) {
        ExternalExchangeRate externalExchangeRate = externalExchangeRateClient.getExternalRate(originCurrency);
        return new ExchangeRate(externalExchangeRate.base(), externalExchangeRate.rates());
    }

}
