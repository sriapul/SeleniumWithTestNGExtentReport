package com.ontestautomation.extentreports.pages;

import org.openqa.selenium.WebDriver;

public class AccountsOverviewPage {
	
	private WebDriver driver;
	
	private final String TITLE = "ParaBank | Accounts Overview";
	
	public AccountsOverviewPage(WebDriver driver) {
		
		this.driver = driver;

	}
	
	public boolean isAt() {
		
		return this.driver.getTitle().equals(TITLE);
	}
}