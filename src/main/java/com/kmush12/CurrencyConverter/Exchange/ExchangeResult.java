package com.kmush12.CurrencyConverter.Exchange;

import java.math.BigDecimal;

public class ExchangeResult {

    private final String originCurrency;
    private final BigDecimal originAmount;
    private final String destinationCurrency;
    private BigDecimal destinationAmount;
    private final BigDecimal rate;

    public ExchangeResult(String originCurrency, BigDecimal originAmount, String destinationCurrency, BigDecimal rate) {
        this.originCurrency = originCurrency;
        this.originAmount = originAmount;
        this.destinationCurrency = destinationCurrency;
        this.rate = rate;
    }

    public BigDecimal getOriginAmount() {
        return originAmount;
    }

    public void setDestinationAmount(BigDecimal destinationAmount) {
        this.destinationAmount = destinationAmount;
    }

    public BigDecimal getRate() {
        return rate;
    }

}
