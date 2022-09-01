package com.kmush12.CurrencyConverter.Exchange;

import java.math.BigDecimal;

public class ExchangeRequest {

    private final String originCurrency;
    private final BigDecimal originAmount;
    private final String destinationCurrency;

    public ExchangeRequest(String originCurrency, BigDecimal originAmount, String destinationCurrency) {
        this.originCurrency = originCurrency;
        this.originAmount = originAmount;
        this.destinationCurrency = destinationCurrency;
    }

    public String getOriginCurrency() {
        return originCurrency;
    }

    public BigDecimal getOriginAmount() {
        return originAmount;
    }

    public String getDestinationCurrency() {
        return destinationCurrency;
    }
}
