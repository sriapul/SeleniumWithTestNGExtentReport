package com.ontestautomation.extentreports.tests;

import java.util.concurrent.TimeUnit;

import com.ontestautomation.extentreports.listener.ExtentReporterNG;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import com.ontestautomation.extentreports.pages.AccountsOverviewPage;
import com.ontestautomation.extentreports.pages.ErrorPage;
import com.ontestautomation.extentreports.pages.LoginPage;

public class LoginTest extends ExtentReporterNG {
	
	 WebDriver driver;
     
	    @BeforeSuite
	    public void setUp() {

	        driver = new FirefoxDriver();
	        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    }
	     
	    @Parameters({"incorrectusername","incorrectpassword"})
	    @Test(description="Performs an unsuccessful login and checks the resulting error message (passes)")
	    public void testFailingLogin(String username, String incorrectpassword) {
	         
	        LoginPage lp = new LoginPage(driver);
	        ErrorPage ep = lp.incorrectLogin(username, incorrectpassword);
	        Assert.assertEquals(ep.getErrorText(), "The username and password could not be verified.");
	    }
	    
	    @Parameters({"incorrectusername","incorrectpassword"})
	    @Test(description="Performs an unsuccessful login and checks the resulting error message (fails)")
	    public void failingTest(String username, String incorrectpassword) {
	         
	        LoginPage lp = new LoginPage(driver);
	        ErrorPage ep = lp.incorrectLogin(username, incorrectpassword);
	        Assert.assertEquals(ep.getErrorText(), "This is not the error message you're looking for.");
	    }
	    
	    @Parameters({"correctusername","correctpassword"})
	    @Test(description="Performs a successful login and checks whether the Accounts Overview page is opened")
	    public void testSuccessfulLogin(String username, String incorrectpassword) {
	         
	        LoginPage lp = new LoginPage(driver);
	        AccountsOverviewPage aop = lp.correctLogin(username, incorrectpassword);
	        Assert.assertEquals(aop.isAt(), true);
	    }

	@AfterTest
	public void afterTest(){


	}
	    @AfterSuite
	    public void tearDown() {
	        driver.quit();
	    }
}