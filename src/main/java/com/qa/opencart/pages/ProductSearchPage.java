package com.qa.opencart.pages;

import com.microsoft.playwright.Page;

public class ProductSearchPage {
	
	Page page;
	
	private static String productLoc = "//img[@title='MacBook' and @class='img-responsive']";
	
	
	
	public ProductSearchPage(Page page) {
		this.page = page;
	}
	
	
	public ProductPage doSelectProduct() {
		page.click(productLoc);
		return new ProductPage(page);
	}
}
