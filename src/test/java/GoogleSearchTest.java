import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.*;

public class GoogleSearchTest {

	public static final String GOOGLE_HOMEPAGE = "http://www.google.com/";
	public static final String MISSPELLED_SEARCH = "the Google search engin";
	public static final String BROWSER_TITLE = "the Google search engin - Google Search";
	public static final String CORRECT_SPELLED_SEARCH = "engine";
	public static final String GOOGLE_SPELLING_SUGGESTION_CSS_SELECTOR = "p.sp_cnt i";
	public static final String WIKIPEDIA_HIT = "Google Search - Wikipedia, the free encyclopedia";
	public static final String WIKIPEDIA_CSS_SELECTOR = "h3.r a";
	public static final String GOOGLE_SIDEBAR_TITLE = "Google";
	public static final String GOOGLE_SIDEBAR_TITLE_CSS_SELECTOR = "div.kno-ecr-pt";
	public static final int IMPLICIT_TIMEOUT_IN_SECONDS = 10;

	@Test
	public void googleGoole() {
		//create a Firefox instance of the WebDriver interface
		WebDriver ffDriver = new FirefoxDriver();
		//managing timeouts
		ffDriver.manage().timeouts().implicitlyWait(IMPLICIT_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS);
		//navigate to Google
		ffDriver.get(GOOGLE_HOMEPAGE);
		//get a reference to the search box
		WebElement searchTxtBox = ffDriver.findElement(By.name("q"));
		//search for the Google search engin
		searchTxtBox.sendKeys(MISSPELLED_SEARCH);
		//pretty much self-explanatory :)
		searchTxtBox.submit();

		//check for browser window
		Boolean isCorrectbrowserTitle = (new WebDriverWait(ffDriver, IMPLICIT_TIMEOUT_IN_SECONDS)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return driver.getTitle().equalsIgnoreCase(BROWSER_TITLE);
			}
		});
		if (isCorrectbrowserTitle) {
			assertEquals(ffDriver.getTitle(), BROWSER_TITLE);
		} else {
			fail("The browser title was incorrect.");
		}
		//check spelling
		assertEquals(ffDriver.findElement(By.cssSelector(GOOGLE_SPELLING_SUGGESTION_CSS_SELECTOR)).getText(), CORRECT_SPELLED_SEARCH);
		//Check wikipedia link which is the first hit
		assertEquals(ffDriver.findElements(By.cssSelector(WIKIPEDIA_CSS_SELECTOR)).get(0).getText(), WIKIPEDIA_HIT);
		//Check right sidebar for a brief description of Google
		assertEquals(ffDriver.findElement(By.cssSelector(GOOGLE_SIDEBAR_TITLE_CSS_SELECTOR)).getText(), GOOGLE_SIDEBAR_TITLE);
		//quit browser session
		ffDriver.quit();
	}

}
