package Task.Automation;

import java.awt.AWTException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class FreeReservation {
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
	// Test Free reservation  
	
    @Test (priority = 1)
    public void freeReservation () throws InterruptedException, AWTException {
        
    	//Select day 10/11
    	WebElement selectDay = driver.findElement(By.xpath("//*[@id=\"um-tenDateSelector\"]/ul/li[10]/button"));
    	selectDay.click(); 
    	Thread.sleep(2000);
    	//Select time slot 7 AM 
    	WebElement selectTimeSlot = driver.findElement(By.xpath("//*[@id=\"timeslot----0\"]"));
        selectTimeSlot.click();
    	Thread.sleep(2000);
    	//Select sky bar 
    	WebElement selectReserveOption = driver.findElement(By.xpath("//*[@id=\"widget-modal\"]/div[3]/section/div[2]/section[1]/section[1]/div/div[1]/div/nav/button[1]"));
    	selectReserveOption.click(); 
    	Thread.sleep(2000);
        // Add data in reservation Form
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
    	WebElement ConfirmBtn =driver.findElement(By.xpath("//*[@id=\"ums-confirm-reservation-details-button\"]"));
    	ConfirmBtn.click();
    	Thread.sleep(3000);
    	// Press on done 
    	WebElement doneBtn = driver.findElement(By.cssSelector("#ums-done-button > span"));
    	//scroll page until done Btn 
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", doneBtn);
    	doneBtn.click();
    	// Assert on Success reservation message 
    	WebElement Message = driver.findElement(By.xpath("/html/body/section[1]/div[2]/div/div/div/h4"));
    	Assert.assertEquals(Message.getText(), "You have a Reservation at KWC Automated");
    	// Assert on Time slot selected 
    	WebElement timeslot = driver.findElement(By.xpath("/html/body/section[2]/div/div/div/ul[1]/li[3]/span"));
    	Assert.assertEquals(timeslot.getText(), "7:00 AM - 9:00 AM");

    }
    // Test Edit Reservation by change time slot 
    @Test (priority = 2)
      public void EditReservation() throws InterruptedException {
    	driver.navigate().refresh();
    	Thread.sleep(1000);
    	//Press on Edit Btn 
    	WebElement editBtn = driver.findElement(By.xpath("//*[@id=\"edit-reservation-button\"]"));
    	editBtn.click();
        Thread.sleep(3000);
        //Select same date of reservation 
        WebElement selectDay = driver.findElement(By.xpath("//*[@id=\"um-tenDateSelector\"]/ul/li[10]/button"));
    	selectDay.click(); 
    	Thread.sleep(2000);
    	//Select another time slot 12PM
    	WebElement selectTimeSlot = driver.findElement(By.xpath("//*[@id=\"timeslot----5\"]"));
        selectTimeSlot.click();
        Thread.sleep(1000);
        //Select sky bar option 
        WebElement selectoption = driver.findElement(By.xpath("//*[@id=\"widget-modal\"]/div[3]/section/div[2]/section[1]/section/div/div[6]/div/nav/button[1]"));
        selectoption.click();
        Thread.sleep(1000);
        //Check reservtion policy 
        WebElement checkBox = driver.findElement(By.xpath("//*[@id=\"um-field-checkbox\"]"));
        checkBox.click();
        Thread.sleep(1000);
        // Press on Confirm 
        WebElement confirmBtn =driver.findElement(By.xpath("//*[@id=\"ums-confirm-reservation-details-button\"]/span"));
        confirmBtn.click();
        Thread.sleep(1000);
      //Join program 
        WebElement yesBtn = driver.findElement(By.xpath("//*[@id=\"widget-modal\"]/div[4]/div/div/section/button[2]"));
        yesBtn.click();
        Thread.sleep(1000);
       //Press on done btn 
        WebElement doneBtn = driver.findElement(By.cssSelector("#ums-done-button > span"));
        //scroll page until done Btn 
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", doneBtn);
    	doneBtn.click();
        //Check slot time message 
    	WebElement slottime = driver.findElement(By.xpath("/html/body/section[2]/div/div/div/ul[1]/li[3]/span"));
    	Assert.assertEquals(slottime.getText(), "12:00 PM - 2:00 PM");

    }
    
    //Test Cancel Reservation 
    @Test (priority = 3)
    public void Cancelreserve () throws InterruptedException {
    	WebElement cancelBtn = driver.findElement(By.xpath("//*[@id=\"cancel-reservation-button\"]"));
    	cancelBtn.click();
    	Thread.sleep(1000);
    	WebElement yesCancel = driver.findElement(By.xpath("//*[@id=\"modal--reservation-cancel\"]/div/div/div[2]/section/a"));
    	yesCancel.click();
    	Thread.sleep(1000);
    	WebElement CancelMessage = driver.findElement(By.xpath("/html/body/section[1]/div[2]/div/div/div[1]/h4"));
    	Assert.assertEquals(CancelMessage.getText(), "Your reservation at KWC Automated has been cancelled.");
    }
    
    
   // Close browser after test
	
 	 @AfterTest
 	 public void afterTest() {
 		driver.close();
 	}
}
