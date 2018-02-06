package com.emirates.currencyexchanges;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emirates.currencyexchanges.model.ExchangeValue;

public interface ExchangeValueRepository extends JpaRepository<ExchangeValue, Long> {

	ExchangeValue findAllByFromAndTo(String from, String to);
}
