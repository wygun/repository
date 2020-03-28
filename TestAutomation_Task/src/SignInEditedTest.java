import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class SignInEditedTest extends SeleniumAutomationTest {

	@Test
	public void signInTest() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("login"))).click();
		String timestamp = String.valueOf(new Date().getTime());
		String email = "gk_" + timestamp + "@gk" + timestamp.substring(7) + ".com";
		String name = "Firstname";
		String surname = "Lastname";
		//I have added below command because it is refreshing the website after clicking the signin button that is why I have rewrite the command
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email_create")));

		driver.findElement(By.id("email_create")).sendKeys(email);
		driver.findElement(By.id("SubmitCreate")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("id_gender2"))).click();
		driver.findElement(By.id("customer_firstname")).sendKeys(name);
		driver.findElement(By.id("customer_lastname")).sendKeys(surname);
		driver.findElement(By.id("passwd")).sendKeys("Qwerty");
		Select select = new Select(driver.findElement(By.id("days")));
		select.selectByValue("1");
		select = new Select(driver.findElement(By.id("months")));
		select.selectByValue("1");
		select = new Select(driver.findElement(By.id("years")));
		select.selectByValue("2000");
		driver.findElement(By.id("company")).sendKeys("Company");
		driver.findElement(By.id("address1")).sendKeys("Qwerty, 123");
		driver.findElement(By.id("address2")).sendKeys("zxcvb");
		driver.findElement(By.id("city")).sendKeys("Qwerty");
		select = new Select(driver.findElement(By.id("id_state")));
		select.selectByVisibleText("Colorado");
		driver.findElement(By.id("postcode")).sendKeys("12345");
		driver.findElement(By.id("other")).sendKeys("Qwerty");
		driver.findElement(By.id("phone")).sendKeys("12345123123");
		driver.findElement(By.id("phone_mobile")).sendKeys("12345123123");
		driver.findElement(By.id("alias")).sendKeys("gk");
		driver.findElement(By.id("submitAccount")).click();

		WebElement heading =
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1")));

		assertEquals(heading.getText(), "MY ACCOUNT");
		assertEquals(driver.findElement(By.className("account")).getText(), name + " " + surname);
		assertTrue(driver.findElement(By.className("info-account")).getText().contains("Welcome to your account."));
		assertTrue(driver.findElement(By.className("logout")).isDisplayed());
		assertTrue(driver.getCurrentUrl().contains("controller=my-account"));
	}
}
