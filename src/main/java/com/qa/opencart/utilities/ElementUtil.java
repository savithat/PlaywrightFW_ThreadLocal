package com.qa.opencart.utilities;


import java.util.List;

import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.SelectOption;
import com.microsoft.playwright.options.WaitForSelectorState;

public class ElementUtil {
	
	private final Page page;

    public ElementUtil(Page page) {
        this.page = page;
    }

    // ---------------------------
    // BASIC LOCATOR UTILITIES
    // ---------------------------

    public Locator get(String selector) {
        return page.locator(selector);
    }

    public Locator getByRole(AriaRole role, String name) {
        return page.getByRole(role, new Page.GetByRoleOptions().setName(name));
    }

    public Locator getByText(String text) {
        return page.getByText(text);
    }

    public Locator getByLabel(String label) {
        return page.getByLabel(label);
    }

    public Locator getByPlaceholder(String placeholder) {
        return page.getByPlaceholder(placeholder);
    }

    // ---------------------------
    // ACTION UTILITIES
    // ---------------------------

    public void click(String selector) {
        page.locator(selector).click();
    }

    public void click(Locator locator) {
        locator.click();
    }

    public void fill(String selector, String value) {
        page.locator(selector).fill(value);
    }

    public String getText(String selector) {
        return page.locator(selector).innerText();
    }

    public String getText(Locator locator) {
        return locator.innerText();
    }
    
    public String getPageTitle() {
        return page.title();
    }

    public String getUrl() {
		return page.url();
	}

    public String getTextboxValueByName(String name) {
        return page.getByRole(
                AriaRole.TEXTBOX,
                new Page.GetByRoleOptions().setName(name).setExact(true)
        ).inputValue();
    }
    
    public String getInnerText(String header) {
		return page.locator(header).innerText();
	}

    
    // ---------------------------
    // WAIT UTILITIES
    // ---------------------------

    public void waitForVisible(String selector) {
        page.locator(selector).waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
    }

    public void waitForHidden(String selector) {
        page.locator(selector).waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.HIDDEN));
    }

    // ---------------------------
    // DROPDOWN UTILITIES
    // ---------------------------

    public void selectByValue(String selector, String value) {
        page.locator(selector).selectOption(value);
    }

    public void selectByLabel(String selector, String label) {
        page.locator(selector).selectOption(new SelectOption().setLabel(label));
    }

    // ---------------------------
    // FRAME UTILITIES
    // ---------------------------

//    public Frame getFrameByName(String name) {
//        return page.frame(new Page.FrameOptions().setName(name));
//    }

    public FrameLocator frameLocator(String frameSelector, String elementSelector) {
        return page.frameLocator(frameSelector);
    }

    // ---------------------------
    // TABLE UTILITIES
    // ---------------------------

    public Locator getRowByText(String tableSelector, String rowText) {
        return page.locator(tableSelector)
                .locator("tr")
                .filter(new Locator.FilterOptions().setHasText(rowText));
    }

    public Locator getCell(String tableSelector, int row, int col) {
        return page.locator(tableSelector)
                .locator("tr").nth(row)
                .locator("td").nth(col);
    }

    // ---------------------------
    // CHAINING UTILITIES
    // ---------------------------

    public Locator chain(String root, String... selectors) {
        Locator loc = page.locator(root);
        for (String sel : selectors) {
            loc = loc.locator(sel);
        }
        return loc;
    }

    public Locator chainWithFilter(String root, String filterText, String finalSelector) {
        return page.locator(root)
                .filter(new Locator.FilterOptions().setHasText(filterText))
                .locator(finalSelector);
    }

    // ---------------------------
    // CHECKBOX / RADIO UTILITIES
    // ---------------------------

    public void check(String selector) {
        page.locator(selector).check();
    }

    public void uncheck(String selector) {
        page.locator(selector).uncheck();
    }

    public boolean isChecked(String selector) {
        return page.locator(selector).isChecked();
    }

    // ---------------------------
    // LIST UTILITIES
    // ---------------------------

    public List<String> getAllTexts(String selector) {
        return page.locator(selector).allInnerTexts();
    }

    public int count(String selector) {
        return page.locator(selector).count();
    }

	

}
