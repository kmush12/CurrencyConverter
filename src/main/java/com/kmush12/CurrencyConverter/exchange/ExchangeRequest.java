package com.kmush12.CurrencyConverter.exchange;

import java.math.BigDecimal;

public record ExchangeRequest(String originCurrency, BigDecimal originAmount, String destinationCurrency) {

}
