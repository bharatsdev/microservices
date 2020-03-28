/**
 * 
 */
package com.ms.auths.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Bharat2010
 *
 */
@RestController
public class GreetingsController {

	@GetMapping({ "/greetings" })
	public String firstPage() {
		return "Greetings from Auth Service!!";
	}
}
