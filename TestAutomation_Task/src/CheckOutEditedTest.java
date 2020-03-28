import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

// I have extended this class with respect to my main class 
public class CheckOutEditedTest extends SeleniumAutomationTest {
	@Test
	public void checkoutTest() {		
		
		logIn();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Women"))).click();
		driver.findElement(By.xpath("//a[@title='Faded Short Sleeve T-shirts']/ancestor::li")).click();
		//Duplication of command that is why I commented out below one
		// driver.findElement(By.xpath("//a[@title='Faded Short Sleeve T-shirts']/ancestor::li")).click();
		//There is no submit button that is why I commented out below command
		// wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("Submit"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[.='Add to cart']"))).click();

		//To read the name of the dress
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("layer_cart_product_title")));
		assertTrue(driver.findElement(By.id("layer_cart_product_title")).getText()
				.contains("Faded Short Sleeve T-shirts"));

		//To read the color and size of the dress
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("layer_cart_product_attributes")));
		assertTrue(driver.findElement(By.id("layer_cart_product_attributes")).getText()
				.contains("Orange, S"));


		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//*[@id='layer_cart']//a[@class and @title='Proceed to checkout']"))).click();


		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//*[contains(@class,'cart_navigation')]/a[@title='Proceed to checkout']"))).click();


		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("processAddress"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("uniform-cgv"))).click();


		driver.findElement(By.name("processCarrier")).click();


		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("bankwire"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='cart_navigation']/button")))
		.click();
		WebElement heading = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1")));

		assertEquals("ORDER CONFIRMATION", heading.getText());


		assertTrue(driver.findElement(By.xpath("//li[@class='step_done step_done_last four']")).isDisplayed());
		assertTrue(driver.findElement(By.xpath("//li[@id='step_end' and @class='step_current last']")).isDisplayed());
		assertTrue(driver.findElement(By.xpath("//*[@class='cheque-indent']/strong")).getText()
				.contains("Your order on My Store is complete."));
		assertTrue(driver.getCurrentUrl().contains("controller=order-confirmation"));
	}
}
