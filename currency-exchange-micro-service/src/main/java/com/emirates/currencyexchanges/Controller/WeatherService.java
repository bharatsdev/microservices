package com.emirates.currencyexchanges.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class WeatherService {
	private static final Logger log = LoggerFactory.getLogger(WeatherService.class);
	private static final String APP_ID = "807672c82a50faa9326ba510c1130a2a";
	private static final String URI_STR = "http://api.openweathermap.org/data/2.5/weather";

	@GetMapping("/")
	private void sayhello() {
		System.out.println("Hello This is my first service");
	}

	@GetMapping("/weather/")
	public void weatherUpdate() {

		RestTemplate restTemplate = new RestTemplate();
		String URI = "";
		UriComponentsBuilder uri = UriComponentsBuilder.fromUriString(URI_STR);
		uri.queryParam("appid", APP_ID);
		uri.queryParam("q", "London,uk");

		String quote = restTemplate.getForObject(uri.build().toUri(), String.class);
		log.info(quote.toString());
	}

}
