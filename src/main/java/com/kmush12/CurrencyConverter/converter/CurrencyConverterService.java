package com.kmush12.CurrencyConverter.converter;

import com.kmush12.CurrencyConverter.calculator.AmountCalculator;
import com.kmush12.CurrencyConverter.currencylist.AvailableCurrency;
import com.kmush12.CurrencyConverter.currencylist.AvailableCurrencyRepository;
import com.kmush12.CurrencyConverter.exchange.*;
import feign.FeignException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurrencyConverterService {

    private final ExchangeRateProvider provider;
    private final AmountCalculator calculator;
    private final ExchangeRepository exchangeRepository;
    private final AvailableCurrencyRepository availableCurrencyRepository;

    public CurrencyConverterService(ExchangeRateProvider provider, AmountCalculator calculator, ExchangeRepository exchangeRepository, AvailableCurrencyRepository availableCurrencyRepository) {
        this.provider = provider;
        this.calculator = calculator;
        this.exchangeRepository = exchangeRepository;
        this.availableCurrencyRepository = availableCurrencyRepository;
    }
    @Scheduled(fixedDelay = 3600000)
    public void refreshDataInDatabase(){
        try {
            insertAvailableCurrency();
            insertExchangeRates2();
        }catch (FeignException e){
            System.out.println("Error connecting to external api");
        }
    }
    public void insertAvailableCurrency(){
        availableCurrencyRepository.insert(provider.getAvailableCurrency());
    }

    public void insertExchangeRates2(){
        getAvailableCurrency2().forEach(availableCurrency -> availableCurrency.symbols().forEach((key, value) -> exchangeRepository.insert(provider.getExchangeRate(key))));
    }
    public List<AvailableCurrency> getAvailableCurrency2() {
        return availableCurrencyRepository.findAll();
    }

    public ExchangeResult converted(ExchangeRequest exchangeRequest) {
        return getExchangeResult(
                exchangeRequest,
                exchangeRepository.findByOriginCurrency(exchangeRequest.originCurrency().toUpperCase()));
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