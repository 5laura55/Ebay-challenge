package com.endava.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ResultsPage {
	private WebDriver driver;
	@FindBy(xpath = "//*[@id='s0-13-11-0-1-2-6-0-6[3]-0-textrange']//input[@aria-label='Minimum Value in COP $']")
	private WebElement miniumPriceLabel;
	@FindBy(xpath = "//*[@id='s0-13-11-0-1-2-6-0-6[3]-0-textrange']//input[@aria-label='Maximum Value in COP $']")
	private WebElement maximumPriceLabel;
	@FindBy(xpath = "//*[@id='srp-river-results']//li[@class='s-item    ']")
	private List<WebElement> elements;

	private final String xpathFilters = "//h3[contains(text(),'%s')]/../..//span[contains(text(),'%s')]";

	public ResultsPage(WebDriver driver) {

		this.driver = driver;
	}

	public void setRangePrices(String minimum, String maximum) {
		miniumPriceLabel.sendKeys(minimum);
		maximumPriceLabel.sendKeys(maximum);
		maximumPriceLabel.sendKeys(Keys.ENTER);
	}

	public void setAFilter(String filter, String value) {
		driver.findElement(By.xpath(String.format(xpathFilters, filter, value))).click();
	}

	public WebElement getMiniumPriceLabel() {
		return miniumPriceLabel;
	}

	public WebElement[] getFirstThreeElements() {
		WebElement[] rsp = new WebElement[3];
		for (int i = 0; i < 3; i++) {
			rsp[i] = elements.get(i);
		}
		return rsp;
	}

	public String[] getFirstThreePrices() {
		String[] rsp = new String[3];
		WebElement[] elements = getFirstThreeElements();
		for (int i = 0; i < elements.length; i++) {
			rsp[i] = (elements[i].findElement(By.className("s-item__price")).getText());
		}

		return rsp;
	}

	public String[] getFirstThreeFilters() {

		String[] rsp = new String[3];
		WebElement[] elements = getFirstThreeElements();
		for (int i = 0; i < elements.length; i++) {
			rsp[i] = elements[i].findElement(By.className("SECONDARY_INFO")).getText();
		}

		return rsp;
	}

	public boolean isFilterCorrect(String filter, String[] filters) {
		boolean rsp = true;
		for (int i = 0; i < filters.length; i++) {
			if (!filters[i].contains(filter)) {
				rsp = false;
			}
		}
		return rsp;
	}

	public boolean arePricesCorrect(double minium, double maximum, String[] prices) {
		boolean rsp = true;

		for (int i = 0; i < prices.length && rsp == true; i++) {
			prices[i] = prices[i].replaceAll("\\$.","");
			System.out.println(prices[i]);

			prices[i] = prices[i].replace(" $", "").replace("COP", "").replace(",", "");
			if (prices[i].contains("to")) {
				double priceOne = Double.parseDouble(prices[i].split(" to ")[0]);
				double priceTwo = Double.parseDouble(prices[i].split(" to ")[1]);
				if (priceOne >= maximum || priceOne <= minium || priceTwo >= maximum || priceTwo <= minium) {
					rsp = false;

				}
			} else {
				double price = Double.parseDouble(prices[i]);
				if (price >= maximum || price <= minium) {
					rsp = false;

				}
			}
			System.out.println(prices[i]);
		}
		return rsp;
	}

}
