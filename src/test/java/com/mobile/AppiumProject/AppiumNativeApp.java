package com.mobile.AppiumProject;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AppiumNativeApp
{
	
	public static void main(String[] args) throws MalformedURLException, InterruptedException {
		
		
		//System.setProperty("webdriver.edge.driver", "resource/msedgedriver.exe");
		//WebDriver driver= new EdgeDriver();
		File f= new File("resource/TheMallApp.apk");
		DesiredCapabilities cap= new DesiredCapabilities();
		cap.setCapability("deviceName", "Redmi");
		cap.setCapability("platformName", "Android");
		cap.setCapability("platformVersion", "7.1.2");
		cap.setCapability("app",f.getAbsolutePath());
		
		WebDriver driver= new RemoteWebDriver(new URL("http://0.0.0.0:4723/wd/hub"), cap);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		WebElement clickKedarBindu=driver.findElement(By.xpath("//android.widget.Button[@resource-id='com.demo.amanora:id/loginGold']"));
		clickKedarBindu.click();
		
		Thread.sleep(20000);
		WebElement clickOffers=driver.findElement(By.xpath("//android.widget.TextView[@resource-id='com.demo.amanora:id/OffersLayout']"));
		clickOffers.click();
		
		Thread.sleep(20000);
		
		driver.quit();
	}

}
