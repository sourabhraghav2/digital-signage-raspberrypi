package com.digitalsinage.action;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import com.digitalsinage.service.ScreenServiceImpl;


public class SingleFrozenDisplayAction  implements Action{
	private static final Logger logger = LogManager.getLogger(ScreenServiceImpl.class);
	private String url;
	public SingleFrozenDisplayAction(String url){
		this .url =url;
	}
	@Override
	public void execute(WebDriver driver) {
		logger.info("inside : execute");
		driver.navigate().to(url);
		driver.manage().window().fullscreen();
	}

}
