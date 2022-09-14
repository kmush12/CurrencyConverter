package com.kmush12.CurrencyConverter.Converter;

import com.fasterxml.jackson.databind.JsonNode;
import com.kmush12.CurrencyConverter.Exchange.ExchangeRateClient;
import com.kmush12.CurrencyConverter.Exchange.ExchangeRequest;
import com.kmush12.CurrencyConverter.Exchange.ExchangeResult;
import com.kmush12.CurrencyConverter.Json.JsonProcessor;
import org.springframework.stereotype.Service;

@Service
public class CurrencyConverterService {

    private final ExchangeRateClient client;
    private final JsonProcessor jsonProcessor;

    public CurrencyConverterService(ExchangeRateClient client, JsonProcessor jsonProcessor) {
        this.client = client;
        this.jsonProcessor = jsonProcessor;
    }

    public JsonNode getCurrencyList() {
        return jsonProcessor.ListOfCurrencies(client.getListOfCurrency());
    }

    public ExchangeResult converted(ExchangeRequest exchangeRequest) {
        return jsonProcessor.getExchangeResult(exchangeRequest,
                client.getRateFromAPI(exchangeRequest.originCurrency().toUpperCase(),
                        exchangeRequest.destinationCurrency().toUpperCase()));
    }

}