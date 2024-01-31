package suselaben;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TestngStart {

	public WebDriver driver;

	@BeforeTest

	public void login() {
		WebDriverManager.chromedriver().setup();

		driver = new ChromeDriver();

		driver.get("https://www.saucedemo.com/v1/index.html");
		driver.findElement(By.xpath("//*[@id=\"user-name\"]")).sendKeys("standard_user");

		driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("secret_sauce");
		driver.findElement(By.xpath("//*[@id=\"login-button\"]")).click();

	}

	@Test(groups = "sorting" , priority = 1)

	public void Test_Sorting_low_to_high() throws InterruptedException {

		driver.findElement(By.xpath("//*[@id=\"inventory_filter_container\"]/select")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id=\"inventory_filter_container\"]/select/option[3]")).click();

		List<WebElement> priceslest = driver.findElements(By.className("inventory_item_price"));

		List<Double> mynewedited = new ArrayList<>(); // ---- this to sort the new values of the list in array list so u
														// can assert
		for (int i = 0; i < priceslest.size(); i++) {

			String price = priceslest.get(i).getText();
			String theEditedPrice = price.replace("$", "");
			double val = Double.parseDouble(theEditedPrice.trim()); // ---to covert double to sting so you can compair
			mynewedited.add(val);
		}

		for (int k = 0; k < mynewedited.size(); k++) {

			boolean check = mynewedited.get(0) < mynewedited.get(mynewedited.size() - 1);

			Assert.assertEquals(check, true);

		}
	}

	@Test(groups = "sorting", priority = 2)

	public void Test_Sorting_high_to_low() throws InterruptedException {
		driver.findElement(By.xpath("//*[@id=\"inventory_filter_container\"]/select")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id=\"inventory_filter_container\"]/select/option[4]")).click();

		List<WebElement> priceslest = driver.findElements(By.className("inventory_item_price"));

		List<Double> mynewedited = new ArrayList<>();                                               // ---- this to sort the new values of the list in array list so u can assert
														
		for (int i = 0; i < priceslest.size(); i++) {

			String price = priceslest.get(i).getText();
			String theEditedPrice = price.replace("$", "");
			double val = Double.parseDouble(theEditedPrice.trim());                    // ---to covert double to sting so you can compair
			mynewedited.add(val);
		}

		Collections.sort(mynewedited, Collections.reverseOrder());
		System.out.println("after revase "+ mynewedited);
		for (int k = 0; k < mynewedited.size(); k++) {

			boolean check = mynewedited.get(0) > mynewedited.get(mynewedited.size() - 1);

			Assert.assertEquals(check, true);  

		}
	}
	
	@Test(groups = "cart")
	
	public void cart() {
		
	}
		
	

}
