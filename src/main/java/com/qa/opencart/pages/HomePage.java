package com.qa.opencart.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.qa.opencart.utilities.ElementUtil;

public class HomePage {

	Page page;
	private ElementUtil elementUtil;

	// 1.Locators
	private String search = "input[name='search']";
//	private String search = "Search";
	private String searchIcon = "//button[@type='button']/parent::span";
//	private String header = "h2:text('New Customer')";
	private String header = "#content h1";
	private String account = "//span[text()='My Account']";
	private String login = "//a[text()='Register']/parent::li//following-sibling::li/a[text()='Login']";

	// 2. Page constructor
	public HomePage(Page page) {
		this.page = page;
		elementUtil = new ElementUtil(page);
	}

	// methods
	public String getHomePageTitle() {
		return elementUtil.getPageTitle();
	}

	public String getUrl() {
		return elementUtil.getUrl();
	}

	public String doSearch(String searchWord) {
//		elementUtil.click(search);
//		elementUtil.fill(search, searchWord);
//		elementUtil.click(searchIcon);
//		return elementUtil.getInnerText(header);
//		Locator searchLoc = elementUtil.getByRole(AriaRole.TEXTBOX, search);
//		elementUtil.click(searchLoc);
//		elementUtil.fill(searchLoc, searchWord);
		
		
		page.click(search);
		page.fill(search, searchWord);
		page.click(searchIcon);
		return page.textContent(header);	
	}
	
	public LoginPage navigateToLoginPage() {
		page.click(account);
		page.click(login);
		return new LoginPage(page);
	}

}
