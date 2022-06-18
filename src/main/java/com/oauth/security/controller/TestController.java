package com.oauth.security.controller;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/debug")
public class TestController {

	@GetMapping("/product")
	public ResponseEntity<?> figureOutIssue() throws UnirestException, ParseException {

		Unirest.setTimeouts(0, 0);
		HttpResponse<String> response = Unirest.get(
				"http://ingress-gateway.gaiansolutions.com/marketplace-service/api/v1.0/products?pageNo=0&pageSize=500&sortBy=id&sortOrder=ASCENDING")
				.asString();

		JSONObject jsonObject = (JSONObject) new JSONParser().parse(response.getBody());

		JSONArray jsonArray = (JSONArray) jsonObject.get("dtos");

		for (int iter = 0; iter < jsonArray.size(); iter++) {
			JSONObject obj = (JSONObject) jsonArray.get(iter);
			for (Object params : obj.keySet()) {
				String param = (String) params;
				if (param.equalsIgnoreCase("activeSince")) {
					ZonedDateTime dateTime1 = Instant.ofEpochMilli((Long) obj.get(param))
							.atZone(ZoneId.of("Australia/Sydney"));
					log.info("---activeSince : {}", dateTime1);
				}

				if (param.equalsIgnoreCase("createdOn")) {
					ZonedDateTime dateTime2 = Instant.ofEpochMilli((Long) obj.get(param))
							.atZone(ZoneId.of("Australia/Sydney"));
					log.info("---activeSince : {}", dateTime2);

				}
			}
		}
		return null;

	}

}
