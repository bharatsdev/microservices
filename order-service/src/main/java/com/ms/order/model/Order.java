package com.ms.order.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 
 * @author Bharat2010
 *
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
	private Long id;
	private String from;
	private String to;
	private BigDecimal conversionMultiple;
	private BigDecimal quantity;
	private BigDecimal totalCalculatedAmount;
	private int port;
}
