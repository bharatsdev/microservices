package com.ms.order.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Bharat2010
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String skuNo;
	private String label;
	private float price;
}
