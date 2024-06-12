package rahulshettyacademy.StepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class StepDefinitionImpl extends BaseTest{
	
	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public ConfirmationPage confirmationPage;
	@Given ("User landed on Ecommerce Page") // cucumber will match from feature file "Given User landed on Ecommerce Page" with "stepDefinitionImpl"
	// class and will execute the method associated with the same statement.
	public void user_landed_on_Ecommerce_Page() throws IOException {
		 landingPage = launchApplication();
	}
	
	//In case of parameterization  "@Given ("User Logged in with username <name> and password <password>")" and Given User Logged in with username ankit86@gmail.com and password Ankit@123 will be matched
	// so we have to make <name> and <password> generic with Regex by writing (.+) in place of <name> and <password>. 
	//to tell system that the whole line is treated as Regex we have to include "^" in front and "$" and at the end of the line as shown below 
	
	
	@Given ("^User Logged in with username (.+) and password (.+)$")
	public void user_logged_in_with_username_and_password(String username, String password)
	{
		productCatalogue = landingPage.loginApplication(username, password);
	}
	
	@When ("^User add product (.+) to cart$")
	public void user_add_product_to_cart(String productName) throws InterruptedException {
		List<WebElement> products =  productCatalogue.getProductList();
		products.forEach(s->System.out.println(s.getText()));
		productCatalogue.addProductToCart(productName);
	}
	
	@When ("^Checkout (.+) and submit the order$")
	//we can also use @And in place of @When
	public void checkout_Submit_Order(String productName) throws InterruptedException {
		CartPage cartPage = productCatalogue.goToCartPage();
		boolean match = cartPage.verifyProductDisplay(productName);
		Assert.assertTrue(match);
		
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("India");
		
		confirmationPage = checkoutPage.submitOrder();
	}
	
	//"THANKYOU FOR THE ORDER." is displayed on Confirmation Page - for this type of sentence where values are passed directly in the sentence
	//we should provide a variable in which this value can be stored for e.g.{string}
	@Then("{string} is displayed on Confirmation Page")
	public void message_Displayed_ConfirmationPage(String string) {
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
	}
	//download "tidy gherkin" plugin of chrome with the help of which stepDefinition is auto generated just by pasting the feature file contents into it.
	
	
	@Then ("{string} message is displayed")
	public void error_Message_is_Displayed(String string) {
		Assert.assertEquals(string ,landingPage.getErrorMessage());
	}
	

}
