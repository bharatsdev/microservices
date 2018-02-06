package com.bot.microservices.translationservice.resources;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.bot.microservices.translationservice.model.TranslateRequestDTO;
import com.neovisionaries.i18n.LanguageCode;

@RestController
@RequestMapping(value = "/travelassistant", produces = MediaType.APPLICATION_JSON_VALUE)
public class TranslationResources {

	private static final String TRANS_APP_KEY = "AIzaSyDuIwA6wSxSGcM2Sl7TQYkksJPWPA6uUVs";
	private static final String TRANS_APP_URL = "https://translation.googleapis.com/language/translate/v2?key=";

	@RequestMapping("/")
	String home() {
		return "Hello World!";
	}

	@RequestMapping(value = "/translateLan", method = RequestMethod.POST)
	public String translateLan(@RequestBody TranslateRequestDTO request) {
		RestTemplate template = new RestTemplate();
		List<LanguageCode> list = LanguageCode.findByName(request.getTarget());

		// Print the language and the ISO 639-2 code.
		String result = template.getForObject(
				TRANS_APP_URL + TRANS_APP_KEY + "&source=en&target=" + list.get(0) + "&q=" + request.getText(),
				String.class);
		return result;
	}

}
