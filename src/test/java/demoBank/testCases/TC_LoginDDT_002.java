package demoBank.testCases;

import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import demoBank.pageObjects.LoginPage;
import demoBank.utils.XLUtils;

public class TC_LoginDDT_002 extends BaseClass {
	
	@Test(dataProvider="LoginData")
	public void LoginDDT(String user, String pwd) throws InterruptedException {
		LoginPage lp = new LoginPage(driver);
		lp.txtUserName(user);
		logger.info("username entered");
		lp.txtPassword(pwd);
		logger.info("password entered");
		lp.btnLogin();
		
		Thread.sleep(3000);
		if(isAlertPresent()==true) {
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();
			Assert.assertTrue(false);
			logger.info("login failed");
		}else {
			Assert.assertTrue(true);
			logger.info("login passed");
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("window.scrollBy(0,1000)");
	        Thread.sleep(3000);
			lp.lnkLogout();
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();	
		}	
	}
	
	public boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}
	
	
	@DataProvider(name="LoginData")
	String [][] getData() throws IOException{
		String path =System.getProperty("user.dir")+"/src/test/java/demoBank/testData/testData.xlsx";
		int rownum=XLUtils.getRowCount(path, "Sheet1");
		int colcount=XLUtils.getCellCount(path, "Sheet1", 1);
		
		String logindata[][]=new String[rownum][colcount];
		for(int i=1;i<rownum;i++) {
			for(int j=0;j<colcount;j++) {
				logindata[i-1][j]=XLUtils.getCellData(path, "Sheet1", i, j);
			}
		}
		return logindata;
	}
}
