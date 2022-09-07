package com.kmush12.CurrencyConverter.Exchange;

import java.math.BigDecimal;

public record ExchangeRate(int id, String originCurrency, String destinationCurrency, BigDecimal rate) {

}
