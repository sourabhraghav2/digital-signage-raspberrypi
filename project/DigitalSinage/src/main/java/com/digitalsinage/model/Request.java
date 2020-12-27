package com.digitalsinage.model;


import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import com.digitalsinage.action.Action;
import com.fasterxml.jackson.annotation.JsonSubTypes;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = As.PROPERTY, property = "requestType")
@JsonSubTypes({
		@JsonSubTypes.Type(value = UpdateUrlRequest.class, name = "UpdateUrlRequest"),
		@JsonSubTypes.Type(value = StopBrowserRequest.class, name = "StopBrowserRequest")})
public abstract class Request {
	protected Action action;
	public Action getAction() {
		return action;
	}

	

	private static final Logger logger = LogManager.getLogger(Request.class);
	public Request() {
	}

	public Request(String requestType, int id, String description) {
		this.requestType = requestType;
		this.description = description;
		this.id = id;
	}
	
	public void performAction(WebDriver driver) {
		logger.info("inside performAction");
		action.execute(driver);
	}

	private String requestType;
	private int id;
	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}public void setAction(Action action) {
		this.action = action;
	}

	public static Logger getLogger() {
		return logger;
	}
	
	

}
