package com.bharat.microservices.currencyexchanges;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bharat.microservices.currencyexchanges.model.ExchangeValue;

public interface ExchangeValueRepository extends JpaRepository<ExchangeValue, Long> {

	ExchangeValue findAllByFromAndTo(String from, String to);
}
