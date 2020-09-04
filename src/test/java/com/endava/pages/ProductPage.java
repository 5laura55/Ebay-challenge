package com.endava.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;

public class ProductPage {
	private WebDriver driver;
	@FindBy(tagName = "h1")
	private WebElement title;
	@FindBy(xpath = "//*[@id='isCartBtn_btn']")
	private WebElement addToCartButton;
public void ProductPage(WebDriver driver){
	this.driver=driver;
}
	public String getTitle() {
		return title.getText();
	}

	public void addToCart() {

		addToCartButton.click();
	}


}
