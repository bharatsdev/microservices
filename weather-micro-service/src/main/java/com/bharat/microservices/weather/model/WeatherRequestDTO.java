package com.bharat.microservices.weather.model;

public class WeatherRequestDTO {
	private Address address;
	private String date_time;
	private String outfit;
	private String unit;

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getDate_time() {
		return date_time;
	}

	public void setDate_time(String date_time) {
		this.date_time = date_time;
	}

	public String getOutfit() {
		return outfit;
	}

	public void setOutfit(String outfit) {
		this.outfit = outfit;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

}
