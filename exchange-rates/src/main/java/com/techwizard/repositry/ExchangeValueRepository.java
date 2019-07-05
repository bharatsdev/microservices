package com.techwizard.repositry;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techwizard.model.ExchangeRates;

public interface ExchangeValueRepository extends JpaRepository<ExchangeRates, Long> {

	ExchangeRates findAllByFromAndTo(String from, String to);
}
