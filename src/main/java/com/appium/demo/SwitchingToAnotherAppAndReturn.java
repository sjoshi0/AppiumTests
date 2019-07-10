package com.appium.demo;

import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;


public class SwitchingToAnotherAppAndReturn {

    private static AndroidDriver<WebElement> driver;

    @Before
    public void init() throws MalformedURLException {


        DesiredCapabilities capabilities;
        capabilities = new DesiredCapabilities();

        capabilities.setCapability("autoAcceptAlerts", true);
        capabilities.setCapability("appium-version", "1.0");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", "8.1");
        capabilities.setCapability("deviceName", "Pixel 2 API 27");
        capabilities.setCapability("app", "/Users/surabhijoshi/optimus_PaytmTests/app/paytm.apk");
        capabilities.setCapability("appPackage", "net.one97.paytm");
        capabilities.setCapability("appActivity", "net.one97.paytm.AJRJarvisSplash");

        URL url = new URL("http://0.0.0.0:4723/wd/hub");

        driver = new AndroidDriver<WebElement>(url, capabilities);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    @Test
    public void test() {

        driver.startActivity(new Activity("com.google.android.apps.messaging", ".ui.appsettings.ApplicationSettingsActivity"));
        System.out.println("Current Activity Package name: " + driver.getCurrentPackage());

        String text = driver.findElementById("android:id/title").getText();
        System.out.println("Text is: " + text);
        Assert.assertEquals("Default SMS app", text);

        driver.startActivity(new Activity("net.one97.paytm", "net.one97.paytm.AJRJarvisSplash"));
        System.out.println("Current Activity Package name: " + driver.getCurrentPackage());
    }

}
