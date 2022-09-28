package com.kmush12.CurrencyConverter.converter;

import com.kmush12.CurrencyConverter.calculator.AmountCalculator;
import com.kmush12.CurrencyConverter.currencylist.AvailableCurrency;
import com.kmush12.CurrencyConverter.exchange.*;
import feign.FeignException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class CurrencyConverterService {

    private final ExchangeRateProvider provider;
    private final AmountCalculator calculator;
    private final ExchangeRepository repository;

    public CurrencyConverterService(ExchangeRateProvider provider, AmountCalculator calculator, ExchangeRepository repository) {
        this.provider = provider;
        this.calculator = calculator;
        this.repository = repository;
    }
    @Scheduled(fixedDelay = 3600000)
    public void refreshExchangeRatesInDatabase(){
        try {
            insertExchangeRates();
        }catch (FeignException e){
            System.out.println("Error connecting to external api");
        }
    }
    public void insertExchangeRates(){
        getCurrencyList().symbols().forEach((key, value) -> repository.insert(provider.getExchangeRate(key)));
        System.out.println("KONIEC");
    }


    public AvailableCurrency getCurrencyList() {
        return provider.getAvailableCurrency();
    }

    public ExchangeResult converted(ExchangeRequest exchangeRequest) {
        return getExchangeResult(
                exchangeRequest,
                repository.findByOriginCurrency(exchangeRequest.originCurrency().toUpperCase()));
    }

    private ExchangeResult getExchangeResult(ExchangeRequest exchangeRequest, ExchangeRate exchangeRate){
        return new ExchangeResult(
                exchangeRate.originCurrency(),
                exchangeRequest.originAmount(),
                exchangeRequest.destinationCurrency().toUpperCase(),
                calculator.calculateDestinationAmount(
                        exchangeRequest.originAmount(),
                        exchangeRate.rates().get(exchangeRequest.destinationCurrency().toUpperCase())),
                exchangeRate.rates().get(exchangeRequest.destinationCurrency()));
    }
}