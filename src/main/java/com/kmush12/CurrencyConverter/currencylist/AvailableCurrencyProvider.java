package com.kmush12.CurrencyConverter.currencylist;

import com.kmush12.CurrencyConverter.exchange.ExternalExchangeRateClient;
import org.springframework.stereotype.Component;

@Component
public class AvailableCurrencyProvider {

    private final ExternalExchangeRateClient externalExchangeRateClient;

    public AvailableCurrencyProvider(ExternalExchangeRateClient externalExchangeRateClient) {
        this.externalExchangeRateClient = externalExchangeRateClient;
    }

    public AvailableCurrency getAvailableCurrency() {
        ExternalAvailableCurrency externalAvailableCurrency = externalExchangeRateClient.getExternalAvailableCurrency();
        return new AvailableCurrency(externalAvailableCurrency.symbols());

    }
}
