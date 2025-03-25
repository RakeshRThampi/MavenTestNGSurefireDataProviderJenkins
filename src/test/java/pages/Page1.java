package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import base.TestBase;

public class Page1 extends TestBase {
	private WebDriver driver;
	private By unBy = By.xpath("//input[@id=\"user-name\"]");
	private By pwBy = By.xpath("//input[@id=\"password\"]");
	private By loginBtnBy = By.id("login-button");
	private By mainMenu = By.xpath ("//*[@id=\"menu_button_container\"]/div/div[3]/div/button");
	private By logOutLink = By.id("logout_sidebar_link");
	
	public Page1(WebDriver driver) {
		System.out.println("Page1() driver = " + driver);
		this.driver = driver;
	}

	public String getPageUrl() {
		System.out.println("Page1::getPageUrl() - URL = " + driver.getCurrentUrl());
		return driver.getCurrentUrl();
	}

	public String loginStandardUser(String username, String password) {
		System.out.println("Page1::loginStandardUser() - User name = " + username + ", Password = " + password);
		driver.findElement(unBy).clear();
		driver.findElement(pwBy).clear();
		driver.findElement(unBy).sendKeys(username);
		driver.findElement(pwBy).sendKeys(password);
		driver.findElement(loginBtnBy).click();
		return driver.getCurrentUrl();
	}

	public String logOutAndGetTitle() {
		waitBy(mainMenu);
		driver.findElement(mainMenu).click();
		waitBy(logOutLink);
		driver.findElement(logOutLink).click();
		return driver.getCurrentUrl();
	}
}
