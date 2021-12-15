package com.mobile.AppiumProject;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.PointOption.point;

public class AppiumNativeAdvance
{
	
	public static void main(String[] args) throws MalformedURLException  {
		
		AppiumNativeAdvance ref= new AppiumNativeAdvance();
		ref.beforeTest();
		ref.CoCoinAppCode();
		ref.afterTest();
		
	}

	AndroidDriver<AndroidElement> driver;
	//AppiumDriver<WebElement> driver;

	public void enterPin()
	{
	
		for (int i = 0; i < 4; i++) {
			WebElement pin1=driver.findElement(By.xpath("//android.widget.FrameLayout[@index='2']"));
			pin1.click();
		}
		
}
	
	public void addingExpense(String value) 
	{
	
		WebElement icon=driver.findElement(By.xpath("//android.widget.TextView[@text='"+value+"']"));
		icon.click();
		
		for (int i = 0; i < 2; i++) {
			WebElement amount=driver.findElement(By.xpath("//android.widget.FrameLayout[@index='2']"));
			amount.click();
		}
		
		WebElement save=driver.findElement(By.xpath("//android.widget.FrameLayout[@index='11']"));
		save.click();
		waitSleep(2000);
}

	public void swipeRightToLeft()
	{
		waitSleep(5000);
		    
		//TouchAction act=new TouchAction(driver);
		Dimension size = driver.manage().window().getSize();
		int anchor = (int) (size.height * 0.7);// 300
		int startPoint = (int) (size.width * 0.7);// (600, 300)
		int endPoint = (int) (size.width * 0.01);// (200, 300)
		new TouchAction(driver).press(point(startPoint, anchor)).waitAction(waitOptions(Duration.ofSeconds(3))).moveTo(point(endPoint, anchor)).release().perform();
		//act.press(ElementOption.element(element,startPoint,anchor)).waitAction(Duration.ofSeconds(3)).moveTo(endPoint,anchor).release().perform();


	}

	public void waitSleep(int sec)
	{
		try {
			Thread.sleep(sec);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void beforeTest() throws MalformedURLException 
	{

		File f= new File("resource/CoCoin.apk");
		
		DesiredCapabilities desiredCapabilities=new DesiredCapabilities();
		desiredCapabilities.setCapability("platformName", "Android");
		desiredCapabilities.setCapability("platformVersion", "7.1.2");
		desiredCapabilities.setCapability("deviceName", "Redmi");
		desiredCapabilities.setCapability("app", f.getAbsolutePath());

		driver= new  AndroidDriver<>(new URL("http://0.0.0.0:4723/wd/hub"), desiredCapabilities);

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	public void CoCoinAppCode()
	{	
		swipeRightToLeft();
		swipeRightToLeft();
		swipeRightToLeft();
		swipeRightToLeft();
		
		waitSleep(5000);
		
		enterPin();
		enterPin();

		addingExpense("Home");
		addingExpense("Lunch");
		addingExpense("Breakfast");
		
		
		WebElement menu=driver.findElement(By.id("com.nightonke.cocoin:id/content_hamburger"));
		menu.click();
		
		enterPin();
		waitSleep(5000);
	}
	

	public void afterTest() 
	{
		driver.quit();
	}
}
