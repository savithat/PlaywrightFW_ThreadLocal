package com.qa.opencart.base;

import java.util.Properties;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.microsoft.playwright.Page;
import com.qa.opencart.factory.PlaywrightFactory;
import com.qa.opencart.pages.AccountPage;
import com.qa.opencart.pages.CheckOutPage;
import com.qa.opencart.pages.HomePage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductPage;
import com.qa.opencart.pages.ProductSearchPage;
import com.qa.opencart.pages.ShoppingCartPage;


public class BaseTest {
	
	protected PlaywrightFactory playwrightFactory;
	protected Page page;
	protected Properties prop;
	
	protected HomePage homePage;
	protected LoginPage loginPage;
	protected AccountPage accountPage;
	protected ProductSearchPage productSearchPage;
	protected ProductPage productPage;
	protected ShoppingCartPage shoppingCartPage;
	protected CheckOutPage checkOutPage;
	
	@Parameters({"browser"})
	@BeforeTest
	public void setUp(@Optional String browserName) {
		
		playwrightFactory = new PlaywrightFactory(); 
		prop = playwrightFactory.intitProperty();
		
		String cmdBrowser = System.getProperty("browserName");
		
		// 2. Fallback logic sequence (Updates 'prop' directly in memory)
		if (cmdBrowser != null && !cmdBrowser.trim().isEmpty()) {
		    // Command line argument is present, overwrite the property in memory
		    prop.setProperty("browser", cmdBrowser.toLowerCase().trim());
		    System.out.println("Using browser from Command Line: " + prop.getProperty("browser"));
		} 
		else if (browserName != null && !browserName.trim().isEmpty()) {
		    // Command line is empty, use and overwrite with the value from testng.xml
		    prop.setProperty("browser", browserName.toLowerCase().trim());
		    System.out.println("Using browser from testng.xml file: " + prop.getProperty("browser"));
		} 
		else {
		    // ULTIMATE FALLBACK: Handles direct IDE runs by reading & sanitizing what's already in the file
		    String existingBrowser = prop.getProperty("browser");
		    if (existingBrowser != null) {
		        prop.setProperty("browser", existingBrowser.toLowerCase().trim());
		    } else {
		        prop.setProperty("browser", "chrome"); // Safety net if the properties file is empty
		    }
		    System.out.println("Using browser from config.properties file: " + prop.getProperty("browser"));
		}
		
		page = playwrightFactory.intitBrowser(prop);
		homePage = new HomePage(page);
	}
	
	@AfterTest
	public void tearDown() {
		if (page != null) page.context().close();
	    if (page != null) page.context().browser().close();
	    if (playwrightFactory.getPlaywright() != null) playwrightFactory.getPlaywright().close();
	//	page.context().browser().close();
	}

}
