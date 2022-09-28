package com.kmush12.CurrencyConverter.exchange;

import org.springframework.stereotype.Component;

@Component
public class ExchangeRateProvider {

    private final ExternalExchangeRateClient externalExchangeRateClient;

    public ExchangeRateProvider(ExternalExchangeRateClient externalExchangeRateClient) {
        this.externalExchangeRateClient = externalExchangeRateClient;
    }

    public ExchangeRate getExchangeRate(String originCurrency, String destinationCurrency) {
        return new ExchangeRate(externalExchangeRateClient.getExternalRate(originCurrency, destinationCurrency).base(), externalExchangeRateClient.getExternalRate(originCurrency, destinationCurrency).rates());
    }

    public AvailableCurrency getAvailableCurrency() {
        return new AvailableCurrency(externalExchangeRateClient.getExternalAvailableCurrency().success(), externalExchangeRateClient.getExternalAvailableCurrency().symbols());

    }
}
