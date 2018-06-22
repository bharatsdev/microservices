package com.techwizard.currencyexchanges;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techwizard.currencyexchanges.model.ExchangeValue;

public interface ExchangeValueRepository extends JpaRepository<ExchangeValue, Long> {

	ExchangeValue findAllByFromAndTo(String from, String to);
}
