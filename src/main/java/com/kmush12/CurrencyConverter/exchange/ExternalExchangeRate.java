package com.kmush12.CurrencyConverter.exchange;

import java.math.BigDecimal;
import java.util.HashMap;

public record ExternalExchangeRate(String base, HashMap<String, BigDecimal> rates) {
}
