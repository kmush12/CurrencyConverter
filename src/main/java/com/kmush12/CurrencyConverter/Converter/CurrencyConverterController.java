package com.kmush12.CurrencyConverter.Converter;




import com.kmush12.CurrencyConverter.Exchange.ExchangeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/convert")

public class CurrencyConverterController {

    private final CurrencyConverterService currencyConverterService;

    @Autowired
    public CurrencyConverterController(CurrencyConverterService currencyConverterService) {
        this.currencyConverterService = currencyConverterService;
    }


    @PostMapping
    @ResponseBody
    public ResponseEntity createExchangeRequest(@RequestBody ExchangeRequest exchangeRequest){


        return new ResponseEntity(
                new ExchangeRequest(
                        exchangeRequest.getOriginCurrency(),
                        exchangeRequest.getOriginAmount(),
                        exchangeRequest.getDestinationCurrency()
                        )
                ,HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity convert(@RequestBody ExchangeRequest exchangeRequest) {
       ExchangeRequest request = new ExchangeRequest(
                exchangeRequest.getOriginCurrency(),
                exchangeRequest.getOriginAmount(),
                exchangeRequest.getDestinationCurrency());
       try{
           return ResponseEntity
                   .status(HttpStatus.OK)
                   .body(currencyConverterService.createExchangeResult(request));
       }catch (IllegalArgumentException e){
           return ResponseEntity
                   .status(HttpStatus.NOT_FOUND)
                   .body(e.getMessage());
       }


    }



}

