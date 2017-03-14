package com.webdriver.chapter10;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class WebDriverAPIPractice {
	public WebDriver driver;

	@BeforeClass
	public void beforeClass(){
		System.setProperty("webdriver.chrome.driver", "D:\\Program Files (x86)\\Selenium\\chromedriver.exe");
		driver = new ChromeDriver();
	}
	
	@AfterClass
	public void afterClass(){
		driver.quit();
	}
	
	//访问某网页地址
	@Test(enabled=false)
	public void visitURL(){
		String baseURL = "http://www.baidu.com";
		driver.get(baseURL+"/");
		try {
			Thread.sleep(3000);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	//返回上一个访问的网页（浏览器的后腿功能）
	@Test(enabled=false)
	public void visitRecentURL(){
		String url1 = "http://www.baidu.com";
		String url2 = "http://www.sogou.com";
		driver.navigate().to(url1+"/");
		driver.navigate().to(url2+"/");
		driver.navigate().back();
		try {
			Thread.sleep(3000);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	//前进到下一个访问的网页（浏览器的前进功能）
	@Test(enabled=false)
	public void visitNextURL(){
		String url1 = "http://www.baidu.com";
		String url2 = "http://www.sogou.com";
		driver.navigate().to(url1+"/");
		driver.navigate().to(url2+"/");
		driver.navigate().back();
		driver.navigate().forward();
		try {
			Thread.sleep(3000);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	//刷新当前页面
	@Test(enabled=false)
	public void freshCurrentPage() throws InterruptedException{
		String url = "http://www.baidu.com";
		driver.navigate().to(url+"/");
		Thread.sleep(3000);
		driver.navigate().refresh();
		try {
			Thread.sleep(3000);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
}
