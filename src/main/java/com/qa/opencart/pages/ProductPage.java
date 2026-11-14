package com.qa.opencart.pages;

import com.microsoft.playwright.Page;

public class ProductPage {
	
	Page page;
	
	private String addToCartButtonLoc = ".form-group #button-cart";
	private String cartButtonLoc = "#cart-total";
	private String ViewCartLinkLoc = "(//p[@class='text-right']/a)[1]";
	
	public ProductPage(Page page) {
		this.page = page;
	}

	
	public void doAddtheProductTocart() {
		page.click(addToCartButtonLoc);
	}
	
	public ShoppingCartPage doviewCart() {
		page.click(cartButtonLoc);
		page.click(ViewCartLinkLoc);
		return new ShoppingCartPage(page);
	}
}
