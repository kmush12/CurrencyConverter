package com.kmush12.CurrencyConverter.Converter;



import com.kmush12.CurrencyConverter.Exchange.ExchangeRate;
import com.kmush12.CurrencyConverter.Exchange.ExchangeRequest;
import com.kmush12.CurrencyConverter.Exchange.ExchangeResult;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Service
public class CurrencyConverterService {

    private ExchangeResult exchangeResult;
   private final List<ExchangeRate> exchangeRatesList = Arrays.asList(new ExchangeRate(0, "EUR", "USD", new BigDecimal("0.96")),
           new ExchangeRate(1, "USD", "EUR", new BigDecimal("1.05")),
           new ExchangeRate(2, "EUR", "PLN", new BigDecimal("4.7")),
           new ExchangeRate(3, "PLN", "EUR", new BigDecimal("0.21")),
           new ExchangeRate(4, "USD", "PLN", new BigDecimal("4.6")),
           new ExchangeRate(5, "PLN", "USD", new BigDecimal("0.2"))
   );

   public ExchangeResult createExchangeResult(ExchangeRequest exchangeRequest) {
       ExchangeRate exchangeRate = getCorrectExchangeRate(exchangeRequest);
       ExchangeResult exchangeResult = new ExchangeResult(exchangeRate.getOriginCurrency(), exchangeRequest.getOriginAmount(), exchangeRequest.getDestinationCurrency(), exchangeRate.getRate());
       exchangeResult.setDestinationAmount(exchangeResult.getOriginAmount().multiply(exchangeResult.getRate()));
       return exchangeResult;
   }

    public ExchangeRate getCorrectExchangeRate(ExchangeRequest exchangeRequest) {
        ExchangeRate correctExchangeRate = null;
         if(setMatchingExchangeRate(exchangeRequest).isPresent()) {

             correctExchangeRate = setMatchingExchangeRate(exchangeRequest).get();
         }else {

         }

        return correctExchangeRate;
    }


    public Optional<ExchangeRate> setMatchingExchangeRate(ExchangeRequest request) {
        return exchangeRatesList.stream()
                .filter(exchangeRate -> exchangeRate.getOriginCurrency().equals(request.getOriginCurrency()) && exchangeRate.getDestinationCurrency().equals(request.getDestinationCurrency()))
                .findFirst();

    }
}
