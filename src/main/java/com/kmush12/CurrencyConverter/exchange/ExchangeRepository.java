package com.kmush12.CurrencyConverter.exchange;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExchangeRepository extends MongoRepository<ExchangeRate, String> {
    Optional<ExchangeRate> findByOriginCurrency(String originCurrency);
}
