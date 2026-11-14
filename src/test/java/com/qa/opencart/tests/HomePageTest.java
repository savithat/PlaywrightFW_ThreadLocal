package com.qa.opencart.tests;

import java.io.File;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.pages.LoginPage;

public class HomePageTest extends BaseTest {

//	@DataProvider
//	public static Object[][] data(){
//		return new Object[][] {
//			{"macbook"},
//			{"iMac"},
//			{"Samsung"}
//			};
//	}

	@DataProvider
	public static Object[][] data() {

		File file = new File("./src/test/resources/testData/searchData.json");
		ObjectMapper objectMapper = new ObjectMapper();

		Object[][] data = null;
		try {
			JsonNode rootNode = objectMapper.readTree(file);
			JsonNode serachNode = rootNode.get("search");
			System.out.println(serachNode.size());

			data = new Object[serachNode.size()][1];

			for (int i = 0; i < serachNode.size(); i++) {
				data[i][0] = serachNode.get(i).asText();
				System.out.println(data[i][0]);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}

	
	
	@Test
	public void homePageTitleTest() {
		String homePageTitle = homePage.getHomePageTitle();
		Assert.assertEquals(homePageTitle, AppConstants.HOME_PAGE_TITLE);
	}

	
	
	@Test(dataProvider = "data")
	public void searchTest(String search) throws InterruptedException {
		String searchHeader = homePage.doSearch(search);
		Thread.sleep(2000);
		System.out.println(searchHeader);
		Assert.assertEquals(searchHeader, "Search - " + search);
	}

	
	
	@Test
	public void homePageUrlTest() {
		String homePageUrl = homePage.getUrl();
		Assert.assertEquals(homePageUrl, AppConstants.HOME_PAGE_URL);
	}
	
}
