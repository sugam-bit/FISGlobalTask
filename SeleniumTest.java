import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.Keys;
import io.github.bonigarcia.wdm.WebDriverManager;


public class SeleniumTest {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 // Set up ChromeDriver using WebDriver Manager
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\ami27\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        
        // Instantiate the Chrome browser
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();  // Maximize the browser window
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);

        try {
            // Step 1: Open the browser and navigate to ebay.com
            driver.get("https://www.ebay.com/");

            // Step 2: Search for 'book'
            WebElement searchBox = driver.findElement(By.className("gh-search-input gh-tb ui-autocomplete-input"));
            searchBox.clear();  // Clear any pre-filled text
            searchBox.sendKeys("book");
            searchBox.sendKeys(Keys.RETURN);  // Press Enter to start searching

            // Step 3: Wait for search results to load and click on the first item
            WebDriverWait wait = new WebDriverWait(driver, 10);
            WebElement firstItem = wait.until(ExpectedConditions.elementToBeClickable(By.className("s-item__title")));
            firstItem.click();

            // Step 4: Click on 'Add to cart' button on the item page
            WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(By.className("ux-call-to-action fake-btn fake-btn--fluid fake-btn--large fake-btn--secondary")));
            addToCartButton.click();

            // Step 5: Wait for the cart to update and verify the cart count
            WebElement cartIcon = wait.until(ExpectedConditions.presenceOfElementLocated(By.className("gh-cart__icon")));
            String cartCount = cartIcon.getText();
            
            // Step 6: Verify that the cart count is 1
            if (cartCount.equals("1")) {
                System.out.println("Test Passed: Item successfully added to the cart.");
            } else {
                System.out.println("Test Failed: Expected cart count '1', but got " + cartCount);
            }
        } catch (Exception e) {
            // Handle any exceptions during the test
            System.out.println("Test Failed: " + e.getMessage());
        } finally {
            // Step 7: Close the browser after the test
            driver.quit();
        }

	}

}
