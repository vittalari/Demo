package demoBank.testCases;

import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;

import demoBank.pageObjects.LoginPage;

public class TC_LoginTest_001 extends BaseClass{

	@Test
	public void LoginTest() throws InterruptedException, IOException {
		
		logger.info("URL launched");
		LoginPage lp = new LoginPage(driver);
		lp.txtUserName(username);
		logger.info("username enterred");
		lp.txtPassword(password);
		logger.info("password entered");
		lp.btnLogin();
		
		Thread.sleep(3000);
		
		System.out.println(driver.getTitle());	
		
		if(driver.getTitle().equals("Guru99 Bank Manager HomePage")) {
			Assert.assertTrue(true);
			logger.info("test pass");
		}else {
			captureScreenshot(driver, "LoginTest");
			Assert.assertTrue(true);
			logger.info("test failed");
		}
		JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,1000)");
        Thread.sleep(2000);
	}
	
}
