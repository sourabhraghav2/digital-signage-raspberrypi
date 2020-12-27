package com.digitalsinage.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.digitalsinage.model.Request;
import com.digitalsinage.service.ScreenService;


@RestController
public class RequestReciever {
	private static final Logger logger = LogManager.getLogger(RequestReciever.class);
	
	@Autowired
	ScreenService service;
	
	@GetMapping("test")
	String test() {
		logger.info("inside : test");
		return "working";
	}

	@PostMapping(path = "receiveRequest", consumes = "application/json")
	boolean receiveRequest(@RequestBody Request request) {
		logger.info("URL : " + request.getId());
		service.addRequest(request);
		return true;
	}

}
