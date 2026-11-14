package com.qa.opencart.pages;

import com.microsoft.playwright.Page;

public class ShoppingCartPage {

	Page page;
	
	private String checkoutButtonLoc = "//a[text()='Checkout']";
	
	
	public ShoppingCartPage(Page page){
		this.page = page;
	}
	
	public CheckOutPage doCheckoutProduct() {
		page.click(checkoutButtonLoc);
		return new CheckOutPage(page);
	}
	
	
}
