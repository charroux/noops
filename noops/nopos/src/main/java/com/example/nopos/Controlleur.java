package com.example.nopos;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class Controlleur {
	
	@Value("${webserviceUrl2}")
	private String webserviceUrl2;
	
	@GetMapping("/")
	public String ok() {
		RestTemplate temp = new RestTemplate();
		return temp.getForEntity(webserviceUrl2, String.class).getBody();
	}

}
