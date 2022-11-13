package seleniumpkg2022MT93076;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class SeleniumTest {
	public WebDriver driver;

	@BeforeTest
	@Parameters("browser")

	public void setup(String browser) throws Exception {
		// Check if parameter passed from TestNG is 'Chrome'
		if (browser.equalsIgnoreCase("chrome")) {
			// set path to chromedriver.exe
			System.setProperty("webdriver.chrome.driver", "E:\\drivers\\chromedriver.exe");
			// create Chrome instance
			driver = new ChromeDriver();
		}
		// Check if parameter passed is 'Edge'
		else if (browser.equalsIgnoreCase("Edge")) {
			// set path to msedgedriver.exe
			System.setProperty("webdriver.edge.driver", "E:\\drivers\\msedgedriver.exe");
			// create Edge instance
			driver = new EdgeDriver();
		} else {
			// If no browser passed throw exception
			throw new Exception("Browser is not correct");
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test(priority = 0)
	public void navigateToWebsite() {

		driver.get("https://www.lumosity.com/en/");
		// Mazimize current window
		driver.manage().window().maximize();
		String title = driver.getTitle();
		System.out.println(title);
	}

	@Test(priority = 1)
	public void loginToPortal() throws InterruptedException {

		driver.findElement(By.linkText("Log In")).click();

		WebElement username = driver.findElement(By.xpath("//input[@name=\"user[login]\"]"));
		username.sendKeys("ptjbcmyesrfwuwbanr@tmmwj.com");
		Thread.sleep(2000);
		System.out.println("Clicked on User login");

		WebElement password = driver.findElement(By.id("user_password"));
		password.sendKeys("Welcome01");
		Thread.sleep(2000);
		System.out.println("Clicked on User password");

		driver.findElement(By.xpath("//input[@value=\"Log In\"]")).click();
		System.out.println("Clicked on Login");
	}

	@Test(priority = 2)
	public void extractWebElements() throws InterruptedException {

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		// Table elements
		List<WebElement> tableElements = driver.findElements(By.tagName("table"));
		System.out.println("Number of table web elements = " + tableElements.size());

		// List elements
		List<WebElement> listElements = driver.findElements(By.tagName("li"));
		System.out.println("Number of list web elements = " + listElements.size());

		List<WebElement> list1 = driver.findElements(By.xpath("//ul[@class=\"nav nav-pills\"]"));

		System.out.println(list1.size());

		for (WebElement webElement : list1) {
			String name = webElement.getText();
			System.out.println(name);
		}
	}

	@Test(priority = 3)
	public void handlingException() {
		try {
			WebElement games = driver.findElement(By.xpath("/html/body/div[2]/div[1]/div/div/div[1]/ul/li[3]/a"));
			games.click();
			System.out.println("clicked");
			// store your parent window
			String parentwindowhandler = driver.getWindowHandle();
			String subwindowhandler = null;
			Set<String> handles = driver.getWindowHandles();
			java.util.Iterator<String> iterator = handles.iterator();
			while (iterator.hasNext()) {
				subwindowhandler = iterator.next();
			}
			// switch to child window
			driver.switchTo().window(subwindowhandler);

			WebElement earlyAccessGames = driver
					.findElement(By.xpath("/html/body/div[5]/div/div/div[2]/div/div[1]/div/div[1]/button"));
			earlyAccessGames.click();
			driver.switchTo().window(parentwindowhandler);
		} catch (ElementNotInteractableException e) {
			System.out.println("This was an example for catching exception");
			;
		} finally {
			System.out.println("This was an example for exception");
		}
	}

	@Test(priority = 5)
	public void cssLocatorInSelenium() {
		driver.findElement(By.cssSelector("span[class='display-name']")).click();
		System.out.println("CSS clicked");
	}

	@Test(priority = 4)
	public void interactionsInWebsite() {
		driver.findElement(By.className("insights")).click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,600)");
		js.executeScript("window.scrollBy(0,-600)");
	}

	@AfterTest
	public void terminateBrowser() {
		driver.close();
	}

}
