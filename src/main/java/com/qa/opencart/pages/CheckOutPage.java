package com.qa.opencart.pages;

import com.microsoft.playwright.Page;


public class CheckOutPage {
	
	
	Page page;
	
	public CheckOutPage(Page page) {
		this.page = page;
	}


	public String getCheckOutPageTitle () {
		return page.title();
	}
	
}
