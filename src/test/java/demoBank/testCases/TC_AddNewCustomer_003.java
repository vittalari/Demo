package demoBank.testCases;

import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;

import demoBank.pageObjects.AddCustomerPage;
import demoBank.pageObjects.LoginPage;
import net.bytebuddy.utility.RandomString;

public class TC_AddNewCustomer_003 extends BaseClass{

	@Test
	public void addNewCustomer() throws IOException, InterruptedException {
		
		LoginPage lp = new LoginPage(driver);
		lp.txtUserName(username);
		logger.info("username entered");
		lp.txtPassword(password);
		logger.info("password entered");
		lp.btnLogin();
		
		Thread.sleep(6000);
	AddCustomerPage addcust = new AddCustomerPage(driver);
	JavascriptExecutor js = ((JavascriptExecutor) driver);
	js.executeScript("scroll(0, 1000);");

		logger.info("customer info sesction......");
		addcust.clickNewCustomer();
		addcust.testCustomerName("santosh");
		addcust.radioGender("male");
		addcust.custDob("01", "01", "2020");
		Thread.sleep(3000);
		addcust.custAddress("rajajinagar");
		addcust.custCity("bangalore");
		addcust.custState("karnataka");
		addcust.custPinno("560010");
		addcust.custPhoneno("777777777");
		String email=randomestring()+"@gmail.com";
		addcust.custEmail(email);
		addcust.custPassword("password");
		addcust.custSubmit();
		
		boolean custCreate = driver.getPageSource().contains("Customer Registered Successfully!!!");
		
		if(custCreate==true) {
			Assert.assertTrue(true);
			logger.info("customer created successfully......");
		}else {
			captureScreenshot(driver,"addNewCustomer");
			Assert.assertTrue(false);
			logger.info("customer creation failed");
	}
}

	public String randomestring() {
		String generatedstring=RandomString.make(8);
		return generatedstring;
	}

}












