package com.qa.opencart.pages;

import com.microsoft.playwright.Page;

public class LoginPage {
	
	Page page;
	
	private String emailAddressLoc = "input[name='email']";
	private String passwordLoc = "input[name='password']";
	private String loginButtonLoc = "input[value='Login']";
	private String forgotPasswordLinkLoc = "//h2[text()='Returning Customer']/parent::div//a[text()='Forgotten Password']";
	private String logOutLinkLoc ="//div[@class='list-group']//a[text()='Logout']";
	
	//constructor
	public LoginPage(Page page) {
		this.page = page;
	}
	
	

	public String getTitle() {	
		return page.title();
	}
	
	
	public AccountPage doLogin(String userName, String passWord) {	
		page.fill(emailAddressLoc, userName);
		page.fill(passwordLoc, passWord);
		page.click(loginButtonLoc);
		return new AccountPage(page);
	}



	public Boolean isforgotPasswordLinkExist() {	
		return page.isVisible(forgotPasswordLinkLoc);
	}

}
