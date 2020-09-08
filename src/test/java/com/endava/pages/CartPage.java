package com.endava.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class CartPage {
	private WebDriver driver;
	@FindBy(tagName = "h3")
	private WebElement title;
     public void CartPage(WebDriver driver){
	this.driver=driver;
}
	public String getTitle() {

	    return title.getText();
	}

}
