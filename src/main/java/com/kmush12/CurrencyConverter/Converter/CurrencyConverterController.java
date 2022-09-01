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



    @GetMapping
    @ResponseBody
    public ResponseEntity convert(@RequestBody ExchangeRequest exchangeRequest) {
       try{
           return ResponseEntity
                   .status(HttpStatus.OK)
                   .body(currencyConverterService.createExchangeResult(exchangeRequest));
       }catch (IllegalArgumentException e){
           return ResponseEntity
                   .status(HttpStatus.NOT_FOUND)
                   .body(e.getMessage());
       } catch (Exception e) {
           throw new RuntimeException(e);
       }


    }



}

