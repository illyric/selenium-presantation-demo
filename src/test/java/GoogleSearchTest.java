import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import static org.testng.Assert.*;

public class GoogleSearchTest {

	public static void main(String[] args) {
		//create a Firefox instance of the WebDriver interface
		WebDriver ffDriver = new FirefoxDriver();
		//navigate to Google
		ffDriver.get("http://www.google.com/");
		//get a reference to the search box
		WebElement searchTxtBox = ffDriver.findElement(By.name("q"));
		//search for the Google search engin
		searchTxtBox.sendKeys("the Google search engin");
		//pretty much self-explanatory :)
		searchTxtBox.submit();
		//check for window title
		assertTrue(ffDriver.getTitle().equals("the Google search engin - Google Search"));
		//check for spelling
		assertEquals((ffDriver.findElement(By.cssSelector("p.sp_cnt i")).getText()), "engine");
		//other checks ...
	}

}
