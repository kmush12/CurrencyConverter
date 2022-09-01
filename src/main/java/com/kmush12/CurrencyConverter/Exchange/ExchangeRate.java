package com.kmush12.CurrencyConverter.Exchange;

import java.math.BigDecimal;

public class ExchangeRate {

    private final int id;
    private final String originCurrency;
    private final String destinationCurrency;
    private final BigDecimal rate;


    public ExchangeRate(int id, String originCurrency, String destinationCurrency, BigDecimal rate) {
        this.id = id;
        this.originCurrency = originCurrency;
        this.destinationCurrency = destinationCurrency;
        this.rate = rate;
    }


    public String getOriginCurrency() {
        return originCurrency;
    }

    public String getDestinationCurrency() {
        return destinationCurrency;
    }

    public BigDecimal getRate() {
        return rate;
    }

}
