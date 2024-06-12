package rahulshettyacademy.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest{

	@Test (groups = {"ErrorHandling"}, retryAnalyzer= rahulshettyacademy.TestComponents.Retry.class)
	public void LoginErrorValidation() throws IOException, InterruptedException {
		
			landingPage.loginApplication("ankit86@gmail.com", "Ankit@12");
			Assert.assertEquals("Incorrect email or password." ,landingPage.getErrorMessage()); //validating negative scenario
		
				
	}
	
	@Test
	public void ProductErrorValidation() throws IOException, InterruptedException {
		
		String productName = "ADIDAS ORIGINAL";
		
		ProductCatalogue productCatalogue = landingPage.loginApplication("ankit86@gmail.com", "Ankit@123");
		
		List<WebElement> products =  productCatalogue.getProductList();
		products.forEach(s->System.out.println(s.getText()));
		productCatalogue.addProductToCart(productName); 
		
		CartPage cartPage = productCatalogue.goToCartPage();
		boolean match = cartPage.verifyProductDisplay("ADIDAS ORIGINAL 1");
		Assert.assertFalse(match); //validating negative scenario

	}
}
