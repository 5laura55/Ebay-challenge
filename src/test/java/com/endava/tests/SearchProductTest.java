package com.endava.tests;



import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.*;

import com.endava.pages.HomePage;
import com.endava.pages.ResultsPage;

public class SearchProductTest extends  BasicTest{


	@Test
	public void setPrices() {
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		homePage.changeLanguage();


		homePage.searchAProduct("air fryer");
		ResultsPage resultsPage = PageFactory.initElements(driver, ResultsPage.class);


		resultsPage.setRangePrices("100000", "300000");

		Assert.assertTrue(resultsPage.arePricesCorrect(100000, 300000, resultsPage.getFirstThreePrices()),"The price exceeds the limits");

	}

	@Test
	public void applyFilter() {
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);

		homePage.changeLanguage();

		homePage.searchAProduct("air fryer");

		ResultsPage resultsPage = PageFactory.initElements(driver, ResultsPage.class);
		resultsPage.setAFilter("Condition", "New");
		Assert.assertTrue(resultsPage.isFilterCorrect("New", resultsPage.getFirstThreeFilters()),"It was not possible to filter correctly");

	}



}
