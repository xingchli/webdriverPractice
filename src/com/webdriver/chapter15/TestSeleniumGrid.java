package com.webdriver.chapter15;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.http.HttpHost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHttpEntityEnclosingRequest;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class TestSeleniumGrid {
	WebDriver driver;
	public static String baseUrl = "http://www.sogou.com/";
	
	public static String nodeURL = "http://10.139.56.215:4444/wd/hub";
	
	@Test
	public void testSogouSearch() throws InterruptedException{
		getNodeIp();
		driver.get(baseUrl);
		driver.findElement(By.id("query")).sendKeys("光荣之路自动化测试");
		driver.findElement(By.id("stb")).click();
		
		//Thread.sleep(15000);
		Assert.assertTrue(driver.getPageSource().contains("光荣之路自动化测试"));
	}
	@BeforeMethod
	public void beforeMethod() throws MalformedURLException{
		DesiredCapabilities capability = DesiredCapabilities.chrome();
		capability.setBrowserName("chrome");
		capability.setPlatform(Platform.WINDOWS);
		driver = new RemoteWebDriver(new URL(nodeURL),capability);
	}
	@AfterMethod
	public void afterMethod(){
		driver.quit();
	}
	
	public void getNodeIp(){
		String hub = "10.139.56.215";
		int port = 4444;
		try {
			HttpHost host = new HttpHost(hub, port);
			DefaultHttpClient client = new DefaultHttpClient();  
			String sessionUrl = "http://" + hub + ":" + port  
                    + "/grid/api/testsession?session=";  
			URL session = new URL(sessionUrl  
                    + ((RemoteWebDriver) driver).getSessionId());
			BasicHttpEntityEnclosingRequest req = new BasicHttpEntityEnclosingRequest("POST",  
                    session.toExternalForm());
			org.apache.http.HttpResponse response = client.execute(host, req);
			String str =EntityUtils.toString(response  
                    .getEntity());
			JSONObject object = new JSONObject(str);
			String proxyID = (String) object.get("proxyId"); 
			System.out.println(proxyID);
			String node = (proxyID.split("//")[1].split(":")[0]);
            System.out.println("WebDriver running in node:"+node);  
            //Logging.log("WebDriver running in node:"+node);  
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
