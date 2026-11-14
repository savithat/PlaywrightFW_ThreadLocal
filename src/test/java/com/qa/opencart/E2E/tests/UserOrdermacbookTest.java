package com.qa.opencart.E2E.tests;

import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class UserOrdermacbookTest extends BaseTest{
	
	
	@Test
	public void userOrdermacbookWithoutApplyDiscountTest(){
		loginPage = homePage.navigateToLoginPage();
		accountPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		productSearchPage = accountPage.doSearchProduct("macbook");
		productPage = productSearchPage.doSelectProduct();
		productPage.doAddtheProductTocart();
		shoppingCartPage = productPage.doviewCart();
		checkOutPage = shoppingCartPage.doCheckoutProduct();
		
		String checkOutPageTitle = checkOutPage.getCheckOutPageTitle();
		System.out.println(checkOutPageTitle);
	}
}
