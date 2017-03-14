package com.webdriver.chapter08;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import org.openqa.selenium.JavascriptExecutor;

public class jQueryTest {
	WebDriver driver;
	JavascriptExecutor js;

	@SuppressWarnings("unchecked")
	@Test
	public void jqueryTest() throws InterruptedException {
		System.setProperty("webdriver.chrome.bin", "C:\\Program Files (x86)\\Google\\Chrome\\Application");
		driver = new ChromeDriver();
		driver.get("http://www.sogou.com/");
		js = (JavascriptExecutor) driver;
		injectjQueryIfNeeded();
		List<WebElement> elements = (List<WebElement>) js.executeScript("return jQuery.find('a')");
		Assert.assertEquals(elements.size(), 122);
		for (int i = 0; i < elements.size(); i++) {
			System.out.println(elements.get(i).getText()+"、");
		}
		driver.close();
	}
	
	public void injectjQueryIfNeeded() {
		if (!jQueryLoaded()) {
			injectjQuery();
		}
	}
	//判断是否已加载jQuery
	public Boolean jQueryLoaded() {
		Boolean loaded;
		try {
			loaded = (Boolean) js.executeScript("return" + "jQuery()!=null");
		} catch (WebDriverException e) {
			loaded = false;
		}
		return loaded;
	}
	
	//通过注入jQuery
	public void injectjQuery(){
		js.executeScript("var headID = document.getElementsByTagName(\"head\")[0];"
						+"var newScript = document.createElement('script');"
						+"newScript.type = 'text/javascript';"
						+"newScript.src = "
						+"'http://ajax.googleapis.com/ajax/"
						+"libs/jqery/1.7.2/jquery.min.js';"
						+"headID.appendChild(newScript);");
	}
}
