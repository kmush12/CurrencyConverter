package com.kmush12.CurrencyConverter.Json;

import com.fasterxml.jackson.databind.JsonNode;
import com.kmush12.CurrencyConverter.Calculator.AmountCalculator;
import com.kmush12.CurrencyConverter.Exchange.ExchangeRate;
import com.kmush12.CurrencyConverter.Exchange.ExchangeRequest;
import com.kmush12.CurrencyConverter.Exchange.ExchangeResult;
import org.springframework.stereotype.Component;

@Component
public class JsonProcessor {

    private final AmountCalculator calculator;

    public JsonProcessor(AmountCalculator calculator) {
        this.calculator = calculator;
    }

    public JsonNode ListOfCurrencies(JsonNode node){
            return node.get("symbols");
        }

        public ExchangeResult getExchangeResult(ExchangeRequest exchangeRequest, ExchangeRate exchangeRate){
            return new ExchangeResult(
                    exchangeRate.originCurrency(),
                    exchangeRequest.originAmount(),
                    exchangeRequest.destinationCurrency(),
                    calculator.calculateDestinationAmount(exchangeRequest.originAmount(), exchangeRate.rates().get(exchangeRequest.destinationCurrency())),
                    exchangeRate.rates().get(exchangeRequest.destinationCurrency()));
        }
}
