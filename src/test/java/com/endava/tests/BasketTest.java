package com.endava.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.endava.pages.CartPage;
import com.endava.pages.HomePage;
import com.endava.pages.ProductPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasketTest {
	private WebDriver driver;

	@BeforeMethod
	public void beforeTest() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://www.ebay.com/");

	}

	@Test
	public void addToBasket() {
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		homePage.changeLanguage();
		wait(2);

		homePage.clickProduct();
		wait(5);
		ProductPage productPage = PageFactory.initElements(driver, ProductPage.class);
		String askedProductname = productPage.getTitle();
		productPage.addToCart();
		CartPage cartPage = PageFactory.initElements(driver, CartPage.class);
		wait(2);

		String productNameInTheBasket = cartPage.getTitle();
		Assert.assertEquals(productNameInTheBasket, askedProductname);

	}
	public void wait(int seconds){
		driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
	}
	@AfterMethod
	public void after() {
		driver.quit();
	}
}
