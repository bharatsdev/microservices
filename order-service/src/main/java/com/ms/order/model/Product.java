package com.ms.order.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Bharat2010
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;
	private String skuNo;
	private String label;
	private float price;

	public Product(String skuNo, String label, float price) {
		super();
		this.skuNo = skuNo;
		this.label = label;
		this.price = price;
	}

}
