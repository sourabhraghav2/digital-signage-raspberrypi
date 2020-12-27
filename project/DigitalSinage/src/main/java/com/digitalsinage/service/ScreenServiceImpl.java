package com.digitalsinage.service;

import java.io.File;
import java.util.Iterator;
import java.util.Vector;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.digitalsinage.constants.AppConstants;
import com.digitalsinage.model.Request;
import com.digitalsinage.properties.Properties;

@Service
public class ScreenServiceImpl implements ScreenService {
	private static Vector<Request> data = new Vector<Request>();
	private static final Logger logger = LogManager.getLogger(ScreenServiceImpl.class);
	private ChromeOptions options = new ChromeOptions();
	private WebDriver driver;
	@Autowired
	public ScreenServiceImpl(Properties appProperties) {
		logger.info("inside : ScreenServiceImpl : Constructor");
		logger.info("Properties name : " + appProperties.getName());
		logger.info("Properties description : " + appProperties.getDescription());
		this.startBrowser();
		this.loadBrowserConfiguration();
		this.loadBrowserDriverToSystem(appProperties);
	}
	
	private void loadBrowserConfiguration(){
		
		options.addArguments(AppConstants.DEBUGGING_PORT);
		options.addArguments(AppConstants.NO_SAND_BOX);
		options.setExperimentalOption(AppConstants.DISABLE_POP_UP_KEY, new String[] { AppConstants.DISABLE_POP_UP_VALUE});
	}

	private void loadBrowserDriverToSystem(Properties properties) {
		logger.info("Driver Location: " + properties.getDriverLocation());
		File file = new File(properties.getDriverLocation());
		String path = file.getAbsolutePath();
		System.setProperty(AppConstants.DRIVER_SYSTEM_PATH, path);
	}
	
	private void loadDefaultUrl() {
		driver.navigate().to(AppConstants.DEFAULT_URL);
		driver.manage().window().fullscreen();
	}
	@Override
	public void startBrowser() {
		logger.info("inside : startBrowser");
		Thread consumer = new Thread(() -> {
			driver= new ChromeDriver(options);
			loadDefaultUrl();
			startListeningRequests();
		});
		consumer.start();
		logger.info("browser started");
	}

	private void startListeningRequests() {
		while (true) {
			synchronized (data) {
				Iterator<Request> it = data.iterator();
				while (it.hasNext()) {
					Request current = (Request) it.next();
					current.performAction(driver);
					it.remove();
				}
			}
		}
	}

	@Override
	public void addRequest(Request request) {
		logger.info("inside addCommand Id : " + request.getId());
		data.add(request);

	}

}
