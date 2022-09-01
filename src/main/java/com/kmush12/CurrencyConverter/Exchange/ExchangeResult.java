package com.kmush12.CurrencyConverter.Exchange;

import java.math.BigDecimal;

public record ExchangeResult(String originCurrency, BigDecimal originAmount, String destinationCurrency,
                             BigDecimal destinationAmount, BigDecimal rate) {

}
