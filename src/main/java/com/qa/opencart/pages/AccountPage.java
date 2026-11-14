package com.qa.opencart.pages;

import com.microsoft.playwright.Page;
import com.qa.opencart.utilities.ElementUtil;

public class AccountPage {
	Page page;
	ElementUtil elementUtil;
	
	private String logOutLoc = "//div[@class='list-group']//a[text()='Logout']";
	private String searchLoc = "input[name='search']";
	private String searchButtonLoc = "span button[type='button']";
	
	
	public AccountPage(Page page){
		this.page = page;
		elementUtil = new ElementUtil(page);
	}
	
	public boolean isLogOutExist() {
		return page.isVisible(logOutLoc);
	}
	
	
	public ProductSearchPage doSearchProduct(String searchString){
		page.fill(searchLoc, searchString);
		page.click(searchButtonLoc);
		return new ProductSearchPage(page);
	}
	
}
