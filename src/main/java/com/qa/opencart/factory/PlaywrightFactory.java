package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Properties;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class PlaywrightFactory {
	
//	 Playwright playwright;
//	 Browser browser;
//	 BrowserContext browserContext;
//	 Page page;
	 
	 static ThreadLocal<Playwright> tLocalPlaywright = new ThreadLocal<>();
	 static ThreadLocal<Browser> tLocalbrowser = new ThreadLocal<>();
	 static ThreadLocal<BrowserContext> tLocalbrowserContext = new ThreadLocal<>();
	 static ThreadLocal<Page> tLocalpage = new ThreadLocal<>();
	 
	 public Playwright getPlaywright() {
		 return tLocalPlaywright.get();
	 }
	 
	 public Browser getBrowser() {
		 return tLocalbrowser.get();
	 }
	 
	 public  BrowserContext getBrowserContext() {
		 return tLocalbrowserContext.get();
	 }
	 
	 public Page getPage() {
		 return tLocalpage.get();
	 }
	 
	
	public Page intitBrowser(Properties prop) {
		
	//	playwright = Playwright.create();
		tLocalPlaywright.set(Playwright.create());
		
		String browserName = prop.getProperty("browser").toLowerCase().trim();
		System.out.println("Browser: "+ prop.getProperty("browser"));
		
		switch (browserName) {
		case "chromium": {
	//		browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
	//		tLocalbrowser.set(playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)));
			tLocalbrowser.set(getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)));	
			break;
		}
		case "firefox": {
	//		browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
	//		tLocalbrowser.set(playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false)));
			tLocalbrowser.set(getPlaywright().firefox().launch(new BrowserType.LaunchOptions().setHeadless(false)));
			break;
		}
		case "chrome": {
	//		browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false));
		//	tLocalbrowser.set(playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false)));
			tLocalbrowser.set(getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false)));
			break;
		}
		case "safari": {
	//		browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false));
// 	tLocalbrowser.set(playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false)));
			tLocalbrowser.set(getPlaywright().webkit().launch(new BrowserType.LaunchOptions().setHeadless(false)));
			break;
		}
		
		default:
			System.out.println("plaease enter the right browser");
		}
		
	/**	browserContext = browser.newContext();
		page = browserContext.newPage();
	//	page.navigate("https://naveenautomationlabs.com/opencart/index.php?route=account/login");
		page.navigate(prop.getProperty("url"));
		return page;  **/
		
		tLocalbrowserContext.set(getBrowser().newContext());
		tLocalpage.set(getBrowserContext().newPage());
		
		getPage().navigate(prop.getProperty("url"));
		return getPage();
	}
	
	//read from preoperties class
	public Properties intitProperty() {
		
		Properties prop = null;
		try {
		File file = new File("./src/test/resources/config/config.properties");
		FileInputStream fip = new FileInputStream(file);
		
		prop = new Properties();	
		prop.load(fip);
		} catch (FileNotFoundException e) {	
			e.printStackTrace();
		}catch (IOException e) {	
			e.printStackTrace();
		}
		
		return prop;
	}
	
	
	public static byte[] attachScreenshot() {
	//	String path = System.getProperty("user.dir")+"/screenshot/"+System.currentTimeMillis()+".peg";
		PlaywrightFactory playwrightFactory = new PlaywrightFactory();
        return playwrightFactory.getPage().screenshot(new Page.ScreenshotOptions()
        		.setPath(Paths.get(System.currentTimeMillis()+".peg"))
				.setFullPage(true));
    }
 
	
}
