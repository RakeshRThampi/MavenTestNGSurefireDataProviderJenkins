package base;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class TestBase {
	protected static WebDriver driver;
	private static WebDriverWait wait;
	private final long IMPL_WAIT = 3;
	private final long EXPL_WAIT = 3;

	@BeforeTest
	@Parameters({ "browser", "url" })
	public void beforeTest(@Optional("Firefox") String browser, String url) {
		System.out.println("TestBase::beforeTest()");
		if (!initialize(browser, url)) {
			System.out.println("TestBase::beforeTest() - Initialization failed");
			System.exit(0);
		}
		wait = new WebDriverWait(driver, Duration.ofSeconds(EXPL_WAIT));
	}

	public boolean initialize(String browser, String url) {
		System.out.println("TestBase::initialize()");
		if (browser.equalsIgnoreCase("Chrome")) {
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("Firefox")) {
			driver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("Edge")) {
			driver = new EdgeDriver();
		} else {
			System.out.println("TestBase::initialize() - No compatibile browser found!");
			return false;
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPL_WAIT));
		driver.manage().window().maximize();
		driver.get(url);
		return true;
	}

	public void waitBy(By by) {
		wait.until(ExpectedConditions.presenceOfElementLocated(by));
	}

	public void waitAndSwitchToFrame(WebElement elem) {
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(elem));
	}

	@AfterTest
	public void afterTest() {
		System.out.println("TestBase::afterTest()");
		if (driver != null) {
			System.out.println("TestBase::afterTest() - Quiting browser...");
			// driver.quit();
		}
	}
}
