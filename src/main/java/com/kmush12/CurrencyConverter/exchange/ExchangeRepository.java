package com.kmush12.CurrencyConverter.exchange;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeRepository extends MongoRepository<ExchangeRate, String> {
}
