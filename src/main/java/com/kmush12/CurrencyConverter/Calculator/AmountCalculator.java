package com.kmush12.CurrencyConverter.Calculator;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class AmountCalculator {

    public BigDecimal calculateDestinationAmount(BigDecimal originAmount, BigDecimal rate) {
        return originAmount.multiply(rate);
    }
}
