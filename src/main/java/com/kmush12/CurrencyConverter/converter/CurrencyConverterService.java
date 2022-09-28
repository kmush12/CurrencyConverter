package com.kmush12.CurrencyConverter.converter;

import com.kmush12.CurrencyConverter.calculator.AmountCalculator;
import com.kmush12.CurrencyConverter.currencylist.AvailableCurrency;
import com.kmush12.CurrencyConverter.currencylist.CurrencyDescription;
import com.kmush12.CurrencyConverter.exchange.*;
import feign.FeignException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Map;

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
        for(Map.Entry<String, CurrencyDescription> symbolsEntry : getCurrencyList().symbols().entrySet()) {
            repository.insert(provider.getExchangeRate(symbolsEntry.getKey()));
        }
    }


    public AvailableCurrency getCurrencyList() {
        return provider.getAvailableCurrency();
    }

    public ExchangeResult converted(ExchangeRequest exchangeRequest) {
        return getExchangeResult(
                exchangeRequest,
                provider.getExchangeRate(
                        exchangeRequest.originCurrency().toUpperCase()));
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