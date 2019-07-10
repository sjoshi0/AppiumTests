package com.appium.demo;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.URL;
import java.util.concurrent.TimeUnit;

public class ParallelTests {

    AndroidDriver<MobileElement> driver;

    private static final String WD_HUB = "http://0.0.0.0:4723/wd/hub";
    private static final String APP = "/Users/surabhijoshi/optimus_PaytmTests/app/paytm.apk";

    @BeforeTest(alwaysRun = true)
    @Parameters({"platform", "udid", "systemPort"})
    public void setup(String platform, String udid, String systemPort) throws Exception {

        URL url = new URL(WD_HUB);
        String[] platformInfo = platform.split(" ");
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("AUTOMATION_NAME", "uiautomator2");
        capabilities.setCapability("platformName", platformInfo[0]);
        capabilities.setCapability("platformVersion", platformInfo[1]);
        capabilities.setCapability("deviceName", "Android phone");
        capabilities.setCapability("UDID", udid);
        capabilities.setCapability("SYSTEM_PORT", systemPort);
        capabilities.setCapability("app", APP);
        capabilities.setCapability("ORIENTATION", "PORTRAIT");
        capabilities.setCapability("NO_RESET", false);
        capabilities.setCapability("appPackage", "net.one97.paytm");
        capabilities.setCapability("appActivity", "net.one97.paytm.AJRJarvisSplash");

        driver = new AndroidDriver<MobileElement>(url, capabilities);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    private void clickElementById(String id) {
        driver.findElementById(id).click();
    }

    private void sendKeys(String elementId, String text) {
        MobileElement element = driver.findElementById(elementId);
        WebDriverWait webDriverWait = new WebDriverWait(driver, 10);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(element));
        element.sendKeys(text);
    }

    @Test
    public void test() {

        //Deny both permission requests
        clickElementById("com.android.packageinstaller:id/permission_deny_button");
        clickElementById("com.android.packageinstaller:id/permission_deny_button");

        sendKeys("net.one97.paytm:id/all_view", "9999999999");
        sendKeys("net.one97.paytm:id/edit_password", "xx**xx**");

        driver.findElementById("net.one97.paytm:id/button_text").click();
    }

}
