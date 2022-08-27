package com.kmush12.CurrencyConverter.Converter;



import com.kmush12.CurrencyConverter.Money.Exchange;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Service
public class CurrencyConverterService {

   private final List<Exchange> exchangeRatesList = Arrays.asList(new Exchange(0, "EUR", "USD", new BigDecimal("0.96")),
           new Exchange(1, "USD", "EUR", new BigDecimal("1.05")),
           new Exchange(2, "EUR", "PLN", new BigDecimal("4.7")),
           new Exchange(3, "PLN", "EUR", new BigDecimal("0.21")),
           new Exchange(4, "USD", "PLN", new BigDecimal("4.6")),
           new Exchange(5, "PLN", "USD", new BigDecimal("0.2"))
   );

    public Exchange createExchange(String fromCode, String toCode, BigDecimal fromCodeAmount) {
        Exchange exchange = setMatchingExchangeRate(fromCode, toCode);
        exchange.setFromCodeAmount(fromCodeAmount);
        exchange.setToCodeAmount(exchange.getExchangeRate().multiply(exchange.getFromCodeAmount()));
        return exchange;
    }

    public Exchange setMatchingExchangeRate(String fromCode, String toCode) {
        return exchangeRatesList.stream()
                .filter(exchangeRate -> exchangeRate.getFromCode().equals(fromCode) && exchangeRate.getToCode().equals(toCode))
                .findFirst()
                .orElse(null);
    }
}
