package com.kmush12.CurrencyConverter.Exchange;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.HashMap;

public record ExchangeRate(@JsonProperty("base") String originCurrency, @JsonProperty("rates") HashMap<String, BigDecimal> rates ) {

}


