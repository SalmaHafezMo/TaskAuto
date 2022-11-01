package Task.Automation;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class PaidReservation {
	   ChromeDriver driver;

	    // Open Chrome browser and navigate to URL 
   
	    @BeforeTest
		public void openURL () throws InterruptedException {
			
			String path = System.getProperty("user.dir")+"\\Driver\\chromedriver.exe";
			System.setProperty("webdriver.chrome.driver", path);
		    driver = new ChromeDriver();
			driver.navigate().to("https://gthewhite.letsumai.com/widget/kwc-automated");
			driver.manage().window().maximize();
			Thread.sleep(5000); //sleep time until loading the page 
		}
	    //Paid reservation
	    @Test
	    public void paidscenario() throws InterruptedException, AWTException {
	      
	    	//Select Partysize 7 people
	    	WebElement selectPeople = driver.findElement(By.xpath("//*[@id=\"um-reservation-party-size\"]/div/div[1]"));
	        selectPeople.click();
			Thread.sleep(1000);
			WebElement option = driver.findElement(By.xpath("//*[@id=\"um-reservation-party-size\"]/div/div[1]/div"));
			Actions builder = new Actions(driver);
			builder.moveToElement(option,0,350).click().perform();
			Thread.sleep(1000);
			
			//Select date 
			WebElement selectDay =driver.findElement(By.xpath("//*[@id=\"um-tenDateSelector\"]/ul/li[12]/button"));
			selectDay.click();
			Thread.sleep(1000);
			//Select Time slot 
            WebElement selectTimeSlot =driver.findElement(By.xpath("//*[@id=\"timeslot-morning-5\"]"));
            selectTimeSlot.click();
			Thread.sleep(1000);
			//Select indoor option 
            WebElement indoorOpt =driver.findElement(By.xpath("//*[@id=\"widget-modal\"]/div[3]/section/div[2]/section[1]/section[1]/div/div[6]/div/nav/button[2]"));
            indoorOpt.click();
			Thread.sleep(1000);
            //Press on proceed 
			WebElement proceed =driver.findElement(By.xpath("//*[@id=\"ums-proceed-to-next-page-button\"]"));
			proceed.click();
			// Add data in Form 
			//Add first name 
	    	WebElement Firstname =driver.findElement(By.xpath("//*[@id=\"um-field--first-name\"]"));
	    	Firstname.clear();
	    	Firstname.sendKeys("Salma");
	    	//Add Last name 
	    	WebElement Lastname = driver.findElement(By.xpath("//*[@id=\"um-field--last-name\"]"));
	    	Firstname.clear();
	    	Lastname.sendKeys("Adel");
	    	//Select Country code 
	    	WebElement SelectCountryCode = driver.findElement(By.className("PhoneInputCountrySelect"));
	    	Select options = new Select(SelectCountryCode);
	    	options.selectByValue("EG");
	    	//Add phone number
	    	WebElement phone = driver.findElement(By.xpath("//*[@id=\"um-field--phone-number\"]"));
	    	phone.clear();
	    	phone.sendKeys("01005848626");
	    	// Add Email 
	    	WebElement Email = driver.findElement(By.xpath("//*[@id=\"um-field--email\"]"));
	    	Email.clear();
	    	Email.sendKeys("Salmahafez584@gmail.com");
	    	//Check Reservation Policy 
	    	WebElement CheckBox =driver.findElement(By.xpath("//*[@id=\"um-field-checkbox\"]"));
	    	CheckBox.click();
	    	// Press Confirm 
	    	WebElement ConfirmBtn =driver.findElement(By.xpath("//*[@id=\"ums-proceed-to-add-payment-details-button\"]"));
	    	ConfirmBtn.click();
	    	Thread.sleep(3000);
	    	// Add payment data 
	    	WebElement CardHolderName =driver.findElement(By.className("StripeElement"));
	    	CardHolderName.sendKeys("Salma");
	    	CardHolderName.sendKeys(Keys.TAB);
	    	Robot rb =new Robot();
	    	StringSelection cardnum = new StringSelection("4242424242424242");
	    	Toolkit.getDefaultToolkit().getSystemClipboard().setContents(cardnum, null);
	    	rb.keyPress(KeyEvent.VK_CONTROL);
	    	rb.keyPress(KeyEvent.VK_V);
	    	rb.keyRelease(KeyEvent.VK_V);
	    	rb.keyRelease(KeyEvent.VK_CONTROL);
	    	
	    	rb.keyPress(KeyEvent.VK_TAB);
	    	rb.keyRelease(KeyEvent.VK_TAB);
	    	Thread.sleep(1000);
	    	
	    	
	    	StringSelection Ex= new StringSelection("12/35");
	    	Toolkit.getDefaultToolkit().getSystemClipboard().setContents(Ex,null);
	    	rb.keyPress(KeyEvent.VK_CONTROL);
	    	rb.keyPress(KeyEvent.VK_V);
	    	rb.keyRelease(KeyEvent.VK_V);
	    	rb.keyRelease(KeyEvent.VK_CONTROL);
	    	
	    	rb.keyPress(KeyEvent.VK_TAB);
	    	rb.keyRelease(KeyEvent.VK_TAB);
	    	Thread.sleep(1000);

	    	StringSelection CVC = new StringSelection("123");
	    	Toolkit.getDefaultToolkit().getSystemClipboard().setContents(CVC,null);
	    	rb.keyPress(KeyEvent.VK_CONTROL);
	    	rb.keyPress(KeyEvent.VK_V);
	    	rb.keyRelease(KeyEvent.VK_V);
	    	rb.keyRelease(KeyEvent.VK_CONTROL);
	    	
            //Press on confirm 
	    	WebElement confirm = driver.findElement(By.xpath("//*[@id=\"ums-confirm-reservation-button\"]"));
	    	confirm.click();
	    	Thread.sleep(3000);

	    	//Press on done btn 
	    	WebElement done =driver.findElement(By.xpath("//*[@id=\"ums-done-button\"]"));
	    	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", done);
	    	done.click();
	    	Thread.sleep(1000);
	    	
	    	//Assert on sucess reservation message 
	    	WebElement message = driver.findElement(By.xpath("/html/body/section[1]/div[2]/div/div/div/h4"));
	    	Assert.assertEquals(message.getText(),"You have a Reservation at KWC Automated");

	    }
	    
}
