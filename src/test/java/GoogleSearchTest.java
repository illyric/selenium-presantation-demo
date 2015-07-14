import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
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

	public static void main(String[] args) {
		//create a Firefox instance of the WebDriver interface
		WebDriver ffDriver = new FirefoxDriver();
		//navigate to Google
		ffDriver.get(GOOGLE_HOMEPAGE);
		//get a reference to the search box
		WebElement searchTxtBox = ffDriver.findElement(By.name("q"));
		//search for the Google search engin
		searchTxtBox.sendKeys(MISSPELLED_SEARCH);
		//pretty much self-explanatory :)
		searchTxtBox.submit();
		//check for window title
		assertTrue(ffDriver.getTitle().equals(BROWSER_TITLE));
		//check for spelling
		assertEquals((ffDriver.findElement(By.cssSelector(GOOGLE_SPELLING_SUGGESTION_CSS_SELECTOR)).getText()), CORRECT_SPELLED_SEARCH);
		//Check wikipedia link which is the first hit
		assertEquals(ffDriver.findElements(By.cssSelector(WIKIPEDIA_CSS_SELECTOR)).get(1).getText(), WIKIPEDIA_HIT);
		//Check right sidebar for a brief description of Google
		assertEquals(ffDriver.findElement(By.cssSelector(GOOGLE_SIDEBAR_TITLE_CSS_SELECTOR)).getText(), GOOGLE_SIDEBAR_TITLE);
		//quit browser session
		ffDriver.quit();
	}

}
