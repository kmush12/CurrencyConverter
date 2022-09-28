package com.kmush12.CurrencyConverter.exchange;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.HashMap;

public record ExchangeRate(String originCurrency, @JsonProperty("rates") HashMap<String, BigDecimal> rates ) {

}


