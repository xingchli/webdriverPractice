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
	
	//����ĳ��ҳ��ַ
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
	
	//������һ�����ʵ���ҳ��������ĺ��ȹ��ܣ�
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
	
	//ǰ������һ�����ʵ���ҳ���������ǰ�����ܣ�
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
	//ˢ�µ�ǰҳ��
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
