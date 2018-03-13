package com.bharat.microservices.weather;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.bharat.microservices.weather.model.WeatherRequestDTO;
import com.bharat.microservices.weather.services.WeatherResponse;

@RestController
@RequestMapping(value = "/travelassistant", produces = MediaType.APPLICATION_JSON_VALUE)
public class WelcomeController {

	private static final String WEATHER_APP_ID = "";
	private static final String WEATHER_APP_URL = "http://api.openweathermap.org/data/2.5/forecast?q=";

	@RequestMapping("/")
	String home() {
		return "Hello World!";
	}

	@RequestMapping(value = "/getWeather", method = RequestMethod.POST)
	public WeatherResponse weatherPost(@RequestBody WeatherRequestDTO request) throws URISyntaxException {

		RestTemplate template = new RestTemplate();
		Map<String, Object> result = template.getForObject(
				WEATHER_APP_URL + request.getAddress().getCity() + "&appid=" + WEATHER_APP_ID + "&units=metric",
				Map.class);
		WeatherResponse res = new WeatherResponse();
		// Integer count = (Integer) ((Map<Integer, Object>) result.get("cnt"));

		List<Object> forecasts = (List<Object>) result.get("list");
		forecasts.stream().forEach(item -> {
			String date = (String) ((Map<String, Object>) item).get("dt_txt");
			if (date.substring(0, 10).equals(request.getDate_time().substring(0, 10))) {
				Map<String, Object> data = (Map<String, Object>) ((Map<String, Object>) item).get("main");
				Double temperature = (Double) data.get("temp");
				List<Object> weather = (List<Object>) ((Map<String, Object>) item).get("weather");
				String weatherData = (String) ((Map<String, Object>) weather.get(0)).get("main");
				Double temp_min = (Double) data.get("temp_min");
				Double temp_max = (Double) data.get("temp_max");
				res.setTemperature(temperature.toString());
				res.setWeather(weatherData);
				res.setTemp_max(temp_max.toString());
				res.setTemp_min(temp_min.toString());
				if ((weatherData.contains("Rain") || weatherData.contains("Clouds"))
						&& "umberlla".contains(request.getOutfit()))
					res.setResponseText("Weather in " + request.getAddress().getCity() + " is Rainy/Cloudy with "
							+ res.getTemperature() + " Degree Celcius.Please carry umberlla..");
				else
					res.setResponseText("Weather in " + request.getAddress().getCity() + " is " + res.getWeather()
							+ " with " + res.getTemperature() + " Degree Celcius");
				res.setCity(request.getAddress().getCity());
				res.setOutfit(request.getOutfit());
			}

		});

		return res;

	}

}
