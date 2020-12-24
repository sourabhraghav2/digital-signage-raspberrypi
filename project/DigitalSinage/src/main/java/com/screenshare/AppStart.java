package com.screenshare;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.Iterator;
import java.util.Vector;

@SpringBootApplication
@RestController
public class AppStart {
	private static final Logger logger = LogManager.getLogger(AppStart.class);
	private static Vector<Request> data = new Vector<Request>();

	@GetMapping("test")
	String test() {
		logger.info("inside : test");
		return "working";
	}

	@PostMapping(path = "receiveRequest", consumes = "application/json")
	boolean receiveRequest(@RequestBody UpdateUrlRequest request) {
		logger.info("URL : " + request.url);
		data.add(request);
		return true;
	}

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "/home/pi/Desktop/driver/working/10.1/chromedriver");
        triggerBrowser();
		SpringApplication.run(AppStart.class, args);
	}

	public static void triggerBrowser() {
		logger.info("triggerBrowser");
		Thread consumer = new Thread(() -> {
//			File file = new File("/src/main/resources/chromedriver.exe");
//			String path = file.getAbsolutePath();
			
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--remote-debugging-port=9222");
			options.addArguments("--no-sandbox");
			
			options.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
			WebDriver driver = new ChromeDriver(options);
			driver.navigate().to("https://player.screen.cloud/browser/index.html");
			driver.manage().window().fullscreen();

			while (true) {
				synchronized (data) {
					Iterator it = data.iterator();
					while (it.hasNext()) {
						Request current = (Request) it.next();
						logger.info("Consuming : id : " + current.id);
						if (current instanceof UpdateUrlRequest) {
							UpdateUrlRequest item = (UpdateUrlRequest) current;
							logger.info("URL  : " + item.url);
							driver.navigate().to(item.url);
							driver.manage().window().fullscreen();
						}
						it.remove();
					}
				}
			}
		});
		consumer.start();

	}

}
