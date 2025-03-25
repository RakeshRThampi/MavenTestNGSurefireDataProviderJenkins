package tests;

import base.TestBase;
import pages.Page1;
import utilities.DataProviderClass;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Test1 extends TestBase {
	private Page1 page1;

	@Test(priority = 1)
	public void testPageLoad() {
		System.out.println("Test1::testPageLoad()");
		page1 = new Page1(driver);
		String expURL = "https://www.saucedemo.com/v1/";
		Assert.assertEquals(page1.getPageUrl(), expURL);
	}

	@Test(priority = 2, dataProvider = "excelCredentials", dataProviderClass = DataProviderClass.class)
	public void loginSwagLabs(String username, String password) {
		System.out.println("Test1::loginSwagLabs() - username = " + username + ", password = " + password);
		String currentURL = "https://www.saucedemo.com/v1/inventory.html";
		Assert.assertEquals(page1.loginStandardUser(username, password), currentURL);

		final String homeURL = "https://www.saucedemo.com/v1/index.html";
		Assert.assertEquals(page1.logOutAndGetTitle(), homeURL);
	}

	/*
	 * public void logOut() { System.out.println("Test1::logOut()"); final String
	 * homeURL = "https://www.saucedemo.com/v1/index.html";
	 * Assert.assertEquals(page1.logOutAndGetTitle(), homeURL); }
	 */
}
