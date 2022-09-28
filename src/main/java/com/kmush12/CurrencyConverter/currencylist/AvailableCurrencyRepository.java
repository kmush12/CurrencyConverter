package com.kmush12.CurrencyConverter.currencylist;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvailableCurrencyRepository extends MongoRepository<AvailableCurrency, String> {

}
