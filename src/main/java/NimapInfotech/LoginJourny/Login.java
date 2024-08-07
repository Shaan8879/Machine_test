package NimapInfotech.LoginJourny;

import static org.testng.Assert.assertEquals;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.*;

public class Login {
    // As per discuss With HR the otp service is not working she stated its dummy website
	//public String baseUrl = "https://testffc.nimapinfotech.com/auth/login";
	//public String baseUrl = "https://testffc.nimapinfotech.com/auth/login";
	public WebDriver driver;
	@BeforeTest
	@Parameters({"URL"}) // Fetch the URL from XML File 
	public void Setup(String URL) {

		driver = new ChromeDriver();
		driver.get(URL);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(240));
	}

	@Test (priority = 1)
	@Parameters({"Name","Mobile","Email"})
	public void SignUp(String Name, String Mobile, String Email) throws InterruptedException {
		
		
		// Find hyperlink and click
		driver.findElement(By.xpath("//a[@routerlink='/auth/register']")).click();
		
		// Find name and sendkeys from parameter
		driver.findElement(By.xpath("//input[@placeholder='Name']")).sendKeys(Name);
		
		// Find Country code dropdown and click
		driver.findElement(By.className("mat-select-value")).click();
		driver.findElement(By.xpath("//input[@placeholder='Search']")).sendKeys("ind");

		// Find all elements present on this page for this class and store in variable
		List<WebElement> options =driver.findElements(By.className("kt-register-select-country-name"));

		
		// Alernation option using for loop 
		for(WebElement option :options)

		{

			// Check all the option contain India 
		if(option.getText().equalsIgnoreCase("India"))

		{

			// Click on the option
		option.click();

		
		//When get this option then exit from the loop
		break;

		}

		}
		
		// Find mobile field and send keys from parameter
		driver.findElement(By.xpath("//input[@placeholder='Mobile No']")).sendKeys(Mobile);
		
		// Find email field and send keys from parameter
		driver.findElement(By.cssSelector("input[formcontrolname='emailid']")).sendKeys(Email);
		
		//Pause the executiom fpr 3 sec for better understanding 
		Thread.sleep(3000);
		
		// Find Back button and click
		driver.findElement(By.id("kt_login_signup_cancel")).click();
		
		
		// As i already mention i'm not able to signUp because it's dummy Website
		
		
		
	}
	
	
	@Test (priority = 2)
	@Parameters({"Username","Password"})
	public void LoginTest(String Username, String Password) {

        // find username field and fetch the user name from XML file using parameterizing
	    driver.findElement(By.xpath("//input[@placeholder='Email Id / Mobile No']")).sendKeys(Username);
	    
	    // find password field and fetch the user name from XML file using parameterizing send password
	    driver.findElement(By.cssSelector("input[placeholder='Password']")).sendKeys(Password);
	    
	    
	    
	    
	    // find Signin button and click the button
	    driver.findElement(By.id("kt_login_signin_submit")).click();
	    
	    // Because of the captcha i'm not able to move forward, in real time testing we can disable the captcha in test envirment

	}
	
	@AfterTest
	public void TearDown() throws InterruptedException {
		
		Thread.sleep(5000); // wait for 5 Sec 
		
		driver.close();
		driver.quit();
		
		
		
	}
	
}
