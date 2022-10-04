package com.kmush12.CurrencyConverter.exchange;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Objects;

@Document
public final class ExchangeRate {
    @Id
    private String id;
    private final String originCurrency;
    private final HashMap<String, BigDecimal> rates;

    public ExchangeRate(String originCurrency, @JsonProperty("rates") HashMap<String, BigDecimal> rates) {
        this.originCurrency = originCurrency;
        this.rates = rates;
    }

    public String originCurrency() {
        return originCurrency;
    }

    @JsonProperty("rates")
    public HashMap<String, BigDecimal> rates() {
        return rates;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (ExchangeRate) obj;
        return Objects.equals(this.id, that.id) &&
                Objects.equals(this.originCurrency, that.originCurrency) &&
                Objects.equals(this.rates, that.rates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, originCurrency, rates);
    }

    @Override
    public String toString() {
        return "ExchangeRate[" +
                "id=" + id + ", " +
                "originCurrency=" + originCurrency + ", " +
                "rates=" + rates + ']';
    }
}


