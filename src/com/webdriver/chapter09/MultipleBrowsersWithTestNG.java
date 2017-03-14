package com.webdriver.chapter09;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class MultipleBrowsersWithTestNG {
	public WebDriver driver;
	String baseUrl = "http://www.sogou.com";

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String Browser) {
		if (Browser.equalsIgnoreCase("firefox")) {
			//System.setProperty("webdriver.firefox.bin", "C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
			driver = new FirefoxDriver();
		} else if (Browser.equalsIgnoreCase("ie")) {
			System.setProperty("webdriver.ie.driver", "D:\\Program Files (x86)\\Selenium\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		} else {
			System.setProperty("webdriver.chrome.driver", "D:\\Program Files (x86)\\Selenium\\chromedriver.exe");
			driver = new ChromeDriver();
		}
	}

	@Test
	public void testSogouSearch() {
		driver.get(baseUrl + "/");
		WebElement inputBox = driver.findElement(By.id("query"));
		Assert.assertTrue(inputBox.isDisplayed());
		inputBox.sendKeys("光荣之路自动化测试");
		//单击搜索按钮
		driver.findElement(By.id("stb")).click();
		//单击后等待3s钟
		try {
			Thread.sleep(3000);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		//判断搜索结果中是否包含搜索关键词
		Assert.assertTrue(driver.getPageSource().contains("光荣之路"));

	}
	
	@AfterClass
	public void afterClass(){
		driver.quit();//关闭打开的浏览器
	}
}
