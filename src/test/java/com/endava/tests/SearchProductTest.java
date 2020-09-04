package com.endava.tests;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.endava.pages.HomePage;
import com.endava.pages.ResultsPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SearchProductTest {
	private WebDriver driver;

	@BeforeMethod
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://www.ebay.com/");

	}

	@Test
	public void setPrices() {
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		homePage.changeLanguage();


		homePage.searchAProduct("air fryer");
		ResultsPage resultsPage = PageFactory.initElements(driver, ResultsPage.class);


		resultsPage.setRangePrices("100000", "300000");

		Assert.assertTrue(resultsPage.arePricesCorrect(100000, 300000, resultsPage.getFirstThreePrices()));

	}

	@Test
	public void applyFilter() {
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);

		homePage.changeLanguage();

		homePage.searchAProduct("air fryer");

		ResultsPage resultsPage = PageFactory.initElements(driver, ResultsPage.class);
		resultsPage.setAFilter("Condition", "New");
		Assert.assertTrue(resultsPage.isFilterCorrect("New", resultsPage.getFirstThreeFilters()));

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();

	}

}
