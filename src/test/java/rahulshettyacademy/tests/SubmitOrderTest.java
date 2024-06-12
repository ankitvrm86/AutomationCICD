package rahulshettyacademy.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.OrderPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest{

	String productName = "ADIDAS ORIGINAL";
	@Test (dataProvider ="getData", groups = {"Purchase"})
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {
		
		String countryName = "India";
		
		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
		
		List<WebElement> products =  productCatalogue.getProductList();
		products.forEach(s->System.out.println(s.getText()));
		productCatalogue.addProductToCart(input.get("productName"));
		
		CartPage cartPage = productCatalogue.goToCartPage();
		boolean match = cartPage.verifyProductDisplay(input.get("productName"));
		Assert.assertTrue(match);
		
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry(countryName);
		
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
				
	}
	
	@Test(dependsOnMethods = {"submitOrder"})
	public void verifyOrder() {
		
		ProductCatalogue productCatalogue =landingPage.loginApplication("ankit86@gmail.com", "Ankit@123");
		OrderPage orderPage = productCatalogue.goToOrderPage();
		boolean match = orderPage.verifyOrderDisplay(productName);
		Assert.assertTrue(match);
	}
	
//	@DataProvider
//	public Object[][] getData(){
//		Object [][] obj = new Object[2][3];
//		
//		obj[0][0] = "ankit86@gmail.com";
//		obj[0][1] = "Ankit@123";
//		obj[0][2] = "ZARA COAT 3";
//		
//		obj[1][0] = "ankit86@gmail.com";
//		obj[1][1] = "Ankit@123";
//		obj[1][2] = "ADIDAS ORIGINAL";
//		return obj;
//		Instead of above code we can write in single line as below
//		return new Object[][] {"ankit86@gmail.com","Ankit@123","ADIDAS ORIGINAL"},{"ankit86@gmail.com","Ankit@123", "ZARA COAT 3"};
//		
//	}

	@DataProvider
	public Object[][] getData() throws IOException {
//		HashMap<String, String> map1 = new HashMap<String, String>();
//		map1.put("email", "ankit86@gmail.com");
//		map1.put("password", "Ankit@123");
//		map1.put("productName", "ADIDAS ORIGINAL");
//		
//		HashMap<String, String> map2 = new HashMap<String, String>();
//		map2.put("email", "ankit86@gmail.com");
//		map2.put("password", "Ankit@123");
//		map2.put("productName", "ZARA COAT 3");
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+ "\\src\\test\\java\\rahulshettyacademy\\data\\PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
		
		
	}
}
