package com.kmush12.CurrencyConverter.Converter;

import com.kmush12.CurrencyConverter.Exchange.ExchangeRate;
import com.kmush12.CurrencyConverter.Exchange.ExchangeRequest;
import com.kmush12.CurrencyConverter.Exchange.ExchangeResult;
import com.kmush12.CurrencyConverter.urlReader.PageReader;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Optional;

@Service
public class CurrencyConverterService {

    @Value("${getdata.url}")
    private String link;

    public String createCurrencyList() throws IOException {
        String pathToCurrencyList = link + "symbols";
        PageReader reader = new PageReader();
        JSONObject all = reader.getPageContent(pathToCurrencyList);
        return all.getJSONObject("symbols").toString();
    }

    public ExchangeResult createExchangeResult(ExchangeRequest exchangeRequest) throws Exception {
        String pathToConversionRate = link + "latest?base=" + exchangeRequest.originCurrency() + "&symbols=" + exchangeRequest.destinationCurrency();
        return getExchangeRateFromAPI(exchangeRequest, pathToConversionRate)
                .map(exchangeRate -> new ExchangeResult(
                        exchangeRate.originCurrency(),
                        exchangeRequest.originAmount(),
                        exchangeRequest.destinationCurrency(),
                        calculateDestinationAmount(exchangeRequest.originAmount(), exchangeRate.rate()),
                        exchangeRate.rate()))
                .orElseThrow(() -> new Exception("Exchange rate for a given currency set cannot be found"));
    }

    private Optional<ExchangeRate> getExchangeRateFromAPI(ExchangeRequest exchangeRequest, String pathToConversionRate) throws IOException {
        PageReader reader = new PageReader();
        JSONObject object = reader.getPageContent(pathToConversionRate);
        return Optional.of(new ExchangeRate(
                object.getString("base"),
                exchangeRequest.destinationCurrency(),
                object.getJSONObject("rates").getBigDecimal(exchangeRequest.destinationCurrency())
        ));
    }

    private BigDecimal calculateDestinationAmount(BigDecimal originAmount, BigDecimal rate) {
        return originAmount.multiply(rate);
    }
}