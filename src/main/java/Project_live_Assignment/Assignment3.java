package Project_live_Assignment;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Assignment3 {

	WebDriver driver;
	String Browser;
	String Url;

	@BeforeClass
	public void readPropertiesFile() {

		Properties propertiesFile = new Properties();

		try {
			InputStream fileInput = new FileInputStream("src\\main\\java\\config\\config.properties");
			propertiesFile.load(fileInput);
			Browser = propertiesFile.getProperty("Browser");
			Url = propertiesFile.getProperty("Url");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@BeforeMethod
	public void launchbrowser() {
		if (Browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver.exe");
			driver = new ChromeDriver();
		} else if (Browser.equalsIgnoreCase("FireFox")) {
			System.setProperty("webdriver.gecko.driver", "Drivers/geckodriver.exe");
			driver = new FirefoxDriver();
		}
		else if (Browser.equalsIgnoreCase("Microsoft Edge")){
			System.setProperty("webdriver.edge.driver", "Drivers/msedgedriver.exe");
			driver = new EdgeDriver();
			
		}
		driver.get(Url);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	@Test(priority = 0)
	public void testCase_One_LoginPage() {

		By User_Name_Element = By.xpath("//input[@id = 'username']");
		By Pass_Word_Element = By.xpath("//input[@id = 'password']");
		By Sign_In_Button_Element = By.xpath("//button[contains(text(),'Sign in')]");
		By Customer_Button = By.xpath("//span[contains(text(),'Customers')]");
		By Add_Customer_Button = By.xpath("//a[contains(text(),'Add Customer')]");
		By Full_Name_Element = By.xpath("//input[@id = 'account']");
		By Company_Element = By.xpath("//select[@id = 'cid']");
		By Email_Element = By.xpath("//input[@id = 'email']");
		By Phone_Element = By.xpath("//input[@id = 'phone']");
		By Address_Element = By.xpath("//input[@id = 'address']");
		By City_Element = By.xpath("//input[@id = 'city']");
		By State_Element = By.xpath("//input[@id = 'state']");
		By Zip_Code_Element = By.xpath("//input[@id = 'zip']");
		By Country_Element = By.xpath("select[@id ='country']");
		//By Country_Element = By.xpath("//span[@id = 'select2-country-container']/ancestor::span[3]/preceding::select[1]");
		By Currency_Element = By.xpath("//select[@id = 'currency']");
		By Group_Element = By.xpath("//select[@id = 'group']");
		By Customer_Password_Element = By.xpath("//input[@id = 'password']");
		By Customer_Confirm_Password_Element = By.xpath("//input[@id = 'cpassword']");
		By Submit_Button_Element = By.xpath("//button[@id = 'submit']");
		By Summary_Page_Element = By.xpath("//a[@id = 'summary']");
		By List_Customers = By.xpath("//a[contains(text(),'List Customers')]");
		By Table_List_Name  = By.xpath(
				"//table[@class='table table-bordered table-hover sys_table footable footable-loaded']/child::tbody[1]/child::tr[1]/child::td[3]/a");

		// LoginData
		String UserName_Input = "demo@techfios.com";
		String Pass_Word_Input = "abc123";
		// Test Data or Mock Data
		String Full_name = "ABCDEFG";
		String Email = "personalmail@gmail.com";
		String phone_number = "123456";
		String Address = "3133 mcdonald dr";
		String city = "Frisco";
		String State = "Texas";
		String ZipCode = "75032";
		String Password_Customer = "123456@ascA";
		String Confirm_Password = "123456@ascA";

		driver.findElement(User_Name_Element).sendKeys(UserName_Input);
		driver.findElement(Pass_Word_Element).sendKeys(Pass_Word_Input);
		driver.findElement(Sign_In_Button_Element).click();
		String Dashboard_Title_Page = driver.getTitle();
		Assert.assertEquals(Dashboard_Title_Page, "Dashboard- iBilling", "Page_NOT_FOund");
		
		
		WebElement Customer_Element = driver.findElement(Customer_Button);
		waitforElement(driver, 30, Customer_Element);		
		Customer_Element.click();
		driver.findElement(Add_Customer_Button).click();
		
		String Contacts_page_Title = driver.getTitle();
		Assert.assertEquals(Contacts_page_Title, "Contacts - iBilling", "Page_NOT_FOund");
		
		WebElement Full_Name_WebElement = driver.findElement(Full_Name_Element);
		waitforElement(driver, 30, Full_Name_WebElement);
		Full_Name_WebElement.sendKeys(Full_name);
		WebElement Company_Dropdown_Element = driver.findElement(Company_Element);
		select_DropDown_value(Company_Dropdown_Element, "Techfios");		
		int random_number = random_number_generate(999); 		
		driver.findElement(Email_Element).sendKeys(random_number+Email);		
		driver.findElement(Phone_Element).sendKeys(random_number +  phone_number );
		driver.findElement(Address_Element).sendKeys(Address);
		driver.findElement(City_Element).sendKeys(city);
		driver.findElement(State_Element).sendKeys(State);
		driver.findElement(Zip_Code_Element).sendKeys(ZipCode);
		WebElement Country_DropDown_Element= driver.findElement(Country_Element);;
		waitforElement(driver, 30, Country_DropDown_Element);
		select_DropDown_value(Country_DropDown_Element,"Afghanistan");
		WebElement Currency_DropDown_Elememt = driver.findElement(Currency_Element);
		select_DropDown_value(Currency_DropDown_Elememt,"USD");
		WebElement Group_DropDown_Elememt = driver.findElement(Group_Element);
		select_DropDown_value(Group_DropDown_Elememt,"January 2021");
		driver.findElement(Customer_Password_Element).sendKeys(Password_Customer);
		driver.findElement(Customer_Confirm_Password_Element).sendKeys(Confirm_Password );
		driver.findElement(Submit_Button_Element).click(); 
		WebElement Summary_page = driver.findElement(Summary_Page_Element);
		waitforElement(driver, 30, Summary_page);	
		String Summary_page_Text = Summary_page.getText();
		Assert.assertEquals(Summary_page_Text , "Summary", "PAGE NOT FOUND");
		driver.findElement(List_Customers).click();
		WebElement List_Customer_Element = driver.findElement(Table_List_Name);
		String Customer_Name_List = List_Customer_Element.getText();
		Assert.assertEquals(Customer_Name_List, Full_name, "NAME NOT FOUND");	

	}

	public void waitforElement(WebDriver driver, int time, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, time);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void select_DropDown_value(WebElement Element, String value) {
		Select sel = new Select(Element);
		sel.selectByVisibleText(value);
	}

	public int random_number_generate(int i) {
		Random ran = new Random();
		int number = ran.nextInt(i);
		return number;
	}
	
	@AfterMethod
	public void tearDown() {
		
		driver.close();
		driver.quit();
	}

}
