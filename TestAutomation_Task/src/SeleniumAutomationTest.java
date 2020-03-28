// Please configure your chrome driver path at below SELENIUM_FOLDER; all tests can be run separately or together
import java.util.HashMap;
// Necessary libraries have been imported
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

// I have deleted WebTest class which was in the original version and created new main class to manage my test cases (login, url, browser and logout) easily from here
public class SeleniumAutomationTest {

	WebDriver driver;
	WebDriverWait wait;

	// I have added protected static final because they are not going to changed during the tests
	protected static final String existingUserEmail = "gk123@gk.com";
	protected static final String existingUserPassword = "123456";
	// I have moved the below string from checkout test case to here
	protected static final String fullName = "Ankit Nigam";
	//to configure URL easily I have added below strings
	protected static final String WEB_SITE = "http://automationpractice.com/index.php";
	protected static final String SELENIUM_FOLDER = "C:\\selenium\\chromedriver.exe";

	//to configure chrome headless mode easily, I have totally changed the below function
	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", SELENIUM_FOLDER);
		ChromeOptions options = new ChromeOptions();
		HashMap<String, Object> chromePreferences = new HashMap<>();
		options.setExperimentalOption("prefs", chromePreferences);
		options.setHeadless(true);
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		wait = new WebDriverWait(driver, 20);
		driver.get(WEB_SITE);
	}

	// I have added logout in set down function to make automation test logout after each test.
	// It is also beneficial to next login because most of the times users are still signed in even though browsers have been closed
	@After
	public void setDown() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".logout"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("login")));
		driver.close();
		driver.quit();
	}
	//Only for refactoring; I have same process in two test cases :)
	public void logIn() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("login"))).click();

		//I have added below command because it is refreshing the website after clicking the signin button that is why I have rewrite the command
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
		driver.findElement(By.id("email")).sendKeys(existingUserEmail);
		driver.findElement(By.id("passwd")).sendKeys(existingUserPassword);
		driver.findElement(By.id("SubmitLogin")).click();
	}
}

