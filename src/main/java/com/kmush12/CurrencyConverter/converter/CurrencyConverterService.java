package com.kmush12.CurrencyConverter.converter;

import com.kmush12.CurrencyConverter.calculator.AmountCalculator;
import com.kmush12.CurrencyConverter.exchange.*;
import org.springframework.stereotype.Service;

@Service
public class CurrencyConverterService {

    private final ExchangeRateProvider provider;
    private final AmountCalculator calculator;

    public CurrencyConverterService(ExchangeRateProvider provider, AmountCalculator calculator) {
        this.provider = provider;
        this.calculator = calculator;
    }

    public AvailableCurrency getCurrencyList() {
        return provider.getAvailableCurrency();
    }

    public ExchangeResult converted(ExchangeRequest exchangeRequest) {
        return getExchangeResult(
                exchangeRequest,
                provider.getExchangeRate(
                        exchangeRequest.originCurrency().toUpperCase(),
                        exchangeRequest.destinationCurrency().toUpperCase()));
    }

    private ExchangeResult getExchangeResult(ExchangeRequest exchangeRequest, ExchangeRate exchangeRate){
        return new ExchangeResult(
                exchangeRate.originCurrency(),
                exchangeRequest.originAmount(),
                exchangeRequest.destinationCurrency(),
                calculator.calculateDestinationAmount(
                        exchangeRequest.originAmount(),
                        exchangeRate.rates().get(exchangeRequest.destinationCurrency())),
                exchangeRate.rates().get(exchangeRequest.destinationCurrency()));
    }

}