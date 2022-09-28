package com.kmush12.CurrencyConverter.exchange;

import com.kmush12.CurrencyConverter.currencylist.AvailableCurrency;
import org.springframework.stereotype.Component;

@Component
public class ExchangeRateProvider {

    private final ExternalExchangeRateClient externalExchangeRateClient;

    public ExchangeRateProvider(ExternalExchangeRateClient externalExchangeRateClient) {
        this.externalExchangeRateClient = externalExchangeRateClient;
    }

    public ExchangeRate getExchangeRate(String originCurrency) {
        return new ExchangeRate(externalExchangeRateClient.getExternalRate(originCurrency).base(), externalExchangeRateClient.getExternalRate(originCurrency).rates());
    }

    public AvailableCurrency getAvailableCurrency() {
        return new AvailableCurrency(externalExchangeRateClient.getExternalAvailableCurrency().success(), externalExchangeRateClient.getExternalAvailableCurrency().symbols());

    }
}
