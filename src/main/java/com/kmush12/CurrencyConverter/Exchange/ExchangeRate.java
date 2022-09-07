package com.kmush12.CurrencyConverter.Exchange;

import java.math.BigDecimal;

public record ExchangeRate(String originCurrency, String destinationCurrency, BigDecimal rate) {

}
