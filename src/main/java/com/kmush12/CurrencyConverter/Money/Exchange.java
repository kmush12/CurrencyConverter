package com.kmush12.CurrencyConverter.Money;

import java.math.BigDecimal;

public class Exchange {

    private final int id;
    private final String fromCode;
    private final String toCode;
    private BigDecimal fromCodeAmount;
    private BigDecimal toCodeAmount;
    private final BigDecimal exchangeRate;


    public Exchange(int id, String fromCode, String toCode, BigDecimal exchangeRate) {
        this.id = id;
        this.fromCode = fromCode;
        this.toCode = toCode;
        this.exchangeRate = exchangeRate;
    }

    public void setToCodeAmount(BigDecimal toCodeAmount) {
        this.toCodeAmount = toCodeAmount;
    }
    public BigDecimal getExchangeRate() {
        return exchangeRate;
    }

    public void setFromCodeAmount(BigDecimal fromCodeAmount) {
        this.fromCodeAmount = fromCodeAmount;
    }

    public String getFromCode() {
        return fromCode;
    }
    public String getToCode() {
        return toCode;
    }
    public BigDecimal getFromCodeAmount() {
        return fromCodeAmount;
    }
    public BigDecimal getToCodeAmount() {
        return toCodeAmount;
    }


}
