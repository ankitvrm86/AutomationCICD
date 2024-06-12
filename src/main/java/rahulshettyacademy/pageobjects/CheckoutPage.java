package rahulshettyacademy.pageobjects;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;
public class CheckoutPage extends AbstractComponent{

	WebDriver driver;
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
	//driver.findElement(By.cssSelector("[placeholder='Select Country']"
	@FindBy(css="[placeholder='Select Country']")
	WebElement country;
	
	//driver.findElement(By.cssSelector(".action__submit")).click();
	@FindBy(css=".action__submit")
	WebElement submit;
	
	//driver.findElements(By.cssSelector(".ta-item span")
	@FindBy(css=".ta-item span")
	List<WebElement> countryList;
	
	By results = By.cssSelector(".ta-results");
	
	public void selectCountry(String countryName) throws InterruptedException {
		Actions a =new Actions(driver);
		a.sendKeys(country, countryName).build().perform();
		waitForElementtoAppear(results);
		countryList.forEach(s->System.out.println(s.getText()));
		countryList.stream().filter(s->s.getText().equalsIgnoreCase(countryName)).forEach(s->s.click());
		JavascriptExecutor js= (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,300)"); 
		Thread.sleep(2000);
	}
	
	public ConfirmationPage submitOrder() {
		submit.click();
		return new ConfirmationPage(driver);
	}
	
}
