package com.endava.tests;

import com.endava.utils.DriverFactory;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.IOException;

public class BasicTest {
    protected WebDriver driver;
    @Parameters("browser")
    @BeforeMethod
    public void setUp(@Optional("Chrome") String browser) {
        driver= DriverFactory.getBrowser(browser);
        driver.get("https://www.ebay.com/");

    }
    @AfterMethod
    public void tearDown() {
        File screenshot=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshot,new File("./target/"+ Math.random()*6 +"screenshot.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        driver.quit();

    }
}
