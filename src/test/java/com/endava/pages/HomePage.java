package com.endava.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;

public class HomePage {
	private WebDriver driver;
	@FindBy(css = "#gh-eb-Geo-a-default > .gh-eb-Geo-txt")
	private WebElement worldIcon;
	@FindBy(css = "#gh-eb-Geo-a-en > span.gh-eb-Geo-txt")
	private WebElement englishLink;
	@FindBy(id = "gh-ac")
	private WebElement searchField;
	@FindBy(id = "gh-btn")
	private WebElement searchButton;
	@FindBy(xpath = "//a[@class='hl-item__link']")
	private WebElement productLink;

	public HomePage(WebDriver driver) {
		this.driver = driver;
	}

	public void changeLanguage() {
		worldIcon.click();
		wait(2);
		englishLink.click();
		wait(2);
	}

	public void searchAProduct(String product) {
		searchField.sendKeys(product);
		searchButton.click();
		wait(2);
	}

	public void clickProduct() {
		productLink.click();
		wait(5);
	}

	public WebDriver getDriver() {
		return driver;
	}
	public void wait(int seconds){
		driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
	}
}
