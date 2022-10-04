package com.kmush12.CurrencyConverter.converter;

import com.kmush12.CurrencyConverter.calculator.AmountCalculator;
import com.kmush12.CurrencyConverter.currencylist.AvailableCurrency;
import com.kmush12.CurrencyConverter.currencylist.AvailableCurrencyProvider;
import com.kmush12.CurrencyConverter.currencylist.AvailableCurrencyRepository;
import com.kmush12.CurrencyConverter.exchange.*;
import feign.FeignException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurrencyConverterService {

    private final ExchangeRateProvider exchangeRateProvider;
    private final AmountCalculator calculator;
    private final ExchangeRepository exchangeRepository;
    private final AvailableCurrencyRepository availableCurrencyRepository;

    private final AvailableCurrencyProvider availableCurrencyProvider;
    public CurrencyConverterService(ExchangeRateProvider exchangeRateProvider, AmountCalculator calculator, ExchangeRepository exchangeRepository, AvailableCurrencyRepository availableCurrencyRepository, AvailableCurrencyProvider availableCurrencyProvider) {
        this.exchangeRateProvider = exchangeRateProvider;
        this.calculator = calculator;
        this.exchangeRepository = exchangeRepository;
        this.availableCurrencyRepository = availableCurrencyRepository;
        this.availableCurrencyProvider = availableCurrencyProvider;
    }
    @Scheduled(fixedDelay = 3600000)
    public void refreshDataInDatabase(){
        try {
            insertAvailableCurrency();
            insertExchangeRates();
            System.out.println("Service is ready to use");
        }catch (FeignException e){
            System.out.println("Error connecting to external api");
        }
    }
    public void insertAvailableCurrency(){
        availableCurrencyRepository.insert(availableCurrencyProvider.getAvailableCurrency());
    }

    public void insertExchangeRates(){
        getAvailableCurrency().forEach(availableCurrency -> availableCurrency.symbols().forEach((key, value) -> exchangeRepository.insert(exchangeRateProvider.getExchangeRate(key))));
    }
    public List<AvailableCurrency> getAvailableCurrency() {
        return availableCurrencyRepository.findAll().stream().toList();
    }

    public ExchangeResult converted(ExchangeRequest exchangeRequest) throws Exception {
        return getExchangeResult(
                exchangeRequest,
                getExchangeRate(exchangeRequest));
    }
    private ExchangeRate getExchangeRate(ExchangeRequest exchangeRequest) throws Exception {
        return exchangeRepository.findByOriginCurrency(exchangeRequest.originCurrency().toUpperCase())
                .map(exchangeRate -> new ExchangeRate(
                        exchangeRate.originCurrency(),
                        exchangeRate.rates()))
                .orElseThrow(() -> new Exception("Exchange rate for a given currency set cannot be found"));
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