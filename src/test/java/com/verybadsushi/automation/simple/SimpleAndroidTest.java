package com.verybadsushi.automation.simple;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SimpleAndroidTest {
	@Test
	public void iCanSeeTheLoginErrorMessage() {
		//define capabilities
		DesiredCapabilities capabilities = new DesiredCapabilities();	
		capabilities.setCapability("automationName", "appium");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("platformVersion", "4.4");
		capabilities.setCapability("deviceName", "");
		capabilities.setCapability("app", "org.wordpress.android");	
		//define Selenium Grid URL
		URL seleniumGridUrl = null;
		try {
			seleniumGridUrl = new URL("http://0.0.0.0:4723/wd/hub");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		//start up an AppiumDriver session
		AppiumDriver ad = new AndroidDriver(seleniumGridUrl, capabilities);
		ad.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//perform the test steps
		ad.findElement(By.id("org.wordpress.android:id/nux_username")).sendKeys("myusername@email.com");
		ad.findElement(By.id("org.wordpress.android:id/nux_password")).sendKeys("password123");
		ad.findElement(By.id("org.wordpress.android:id/nux_sign_in_button")).click();
		Assert.assertEquals(ad.findElement(By.id("org.wordpress.android:id/nux_dialog_title")).getText(), "We can't log you in");
		//close the WebDriver session
		ad.quit();
	}
}
