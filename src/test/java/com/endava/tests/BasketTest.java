package com.endava.tests;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.*;

import com.endava.pages.CartPage;
import com.endava.pages.HomePage;
import com.endava.pages.ProductPage;



public class BasketTest extends BasicTest{


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
		wait(30);

		String productNameInTheBasket = cartPage.getTitle();
		Assert.assertEquals(productNameInTheBasket, askedProductname,"It was not possible to add the product to the cart");

	}
	public void wait(int seconds){
		driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
	}




}
