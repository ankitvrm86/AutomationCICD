package rahulshettyacademy.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.pageobjects.LandingPage;
	
public class BaseTest {
	
	
	public WebDriver driver;
	public LandingPage landingPage;
	public WebDriver initializeDriver() throws IOException {
		
		Properties prop =  new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\rahulshettyacademy\\resources\\GlobalData.properties");
		prop.load(fis);
		String browserName = System.getProperty("browser")!=null? System.getProperty("browser"): prop.getProperty("browser");
//		String browserName = prop.getProperty("browser");
		
		
		if(browserName.contains("chrome")) 
		{
				WebDriverManager.chromedriver().clearDriverCache().setup();
				ChromeOptions options = new ChromeOptions();
//				options.addArguments("start-maximized");
				if(browserName.contains("headless")){
				options.addArguments("headless");
				}
				driver = new ChromeDriver(options);
				driver.manage().window().setSize(new Dimension (1440,900)); //full screen 
				
				
		} else if (browserName.equalsIgnoreCase("firefox"))
		{
				WebDriverManager.firefoxdriver().clearDriverCache().setup();
				FirefoxOptions options = new FirefoxOptions();
				options.addArguments("start-maximized");
				driver = new FirefoxDriver(options);
				
		}else if(browserName.equalsIgnoreCase("edge"))
		{
			WebDriverManager.edgedriver().clearDriverCache().setup();
			EdgeOptions options = new EdgeOptions();
			options.addArguments("start-maximized");
			driver = new EdgeDriver(options);
			
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
	}
	
	@BeforeMethod (alwaysRun = true)
	public LandingPage launchApplication() throws IOException
	{
		driver = initializeDriver();
		landingPage = new LandingPage(driver);
		landingPage.goTo();
		return landingPage;
	}
	
	@AfterMethod (alwaysRun = true)
	public void tearDown() {
		driver.close();
	}
	
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {

		String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>(){
		});
		return data;
	}
	
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
		
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(source, new File(System.getProperty("user.dir")+ "//reports//" + testCaseName + ".png"));
		
		return (System.getProperty("user.dir")+ "//reports//" + testCaseName + ".png");
	}

}
