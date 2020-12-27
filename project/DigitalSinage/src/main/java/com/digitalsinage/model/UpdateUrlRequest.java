package com.digitalsinage.model;

import org.openqa.selenium.WebDriver;
import com.digitalsinage.action.SingleFrozenDisplayAction;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("UpdateUrlRequest")
public class UpdateUrlRequest extends Request {
	private String url;
	public UpdateUrlRequest () {}
	

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
		setAction(new SingleFrozenDisplayAction(url));
	}
	public void performAction(WebDriver driver) {
		super.setAction(new SingleFrozenDisplayAction(url));
		action.execute(driver);
	}
	
}
