package com.bot.microservices.weatherservice.services;

public class WeatherResponse {
	private String temperature;
	private String weather;
	private String temp_min;
	private String temp_max;
	private String responseText;
	private String city;
	private String outfit;

	public String getTemperature() {
		return temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	public String getWeather() {
		return weather;
	}

	public void setWeather(String weather) {
		this.weather = weather;
	}

	public String getTemp_min() {
		return temp_min;
	}

	public void setTemp_min(String temp_min) {
		this.temp_min = temp_min;
	}

	public String getTemp_max() {
		return temp_max;
	}

	public void setTemp_max(String temp_max) {
		this.temp_max = temp_max;
	}

	public String getResponseText() {
		return responseText;
	}

	public void setResponseText(String responseText) {
		this.responseText = responseText;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getOutfit() {
		return outfit;
	}

	public void setOutfit(String outfit) {
		this.outfit = outfit;
	}

}
