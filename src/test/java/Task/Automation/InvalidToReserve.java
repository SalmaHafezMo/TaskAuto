package Task.Automation;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class InvalidToReserve {

    ChromeDriver driver;
    
	// Open Chrome browser and navigate to URL 
    
	@BeforeTest
	public void openURL () throws InterruptedException {
		
		String path = System.getProperty("user.dir")+"\\Driver\\chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", path);
	    driver = new ChromeDriver();
		driver.navigate().to("https://gthewhite.letsumai.com/widget/kwc-automated");
		driver.manage().window().maximize();
		Thread.sleep(3000); //sleep time until loading the page 
	}
    
	// Test that user cannot reserve after one month  
	
	@Test(priority = 1)
	public void CheckReservation () throws InterruptedException {
		
		WebElement ChangeCalendar = driver.findElement(By.xpath("//*[@id=\"um-reservation-date-picker\"]"));
		ChangeCalendar.click();
		Thread.sleep(1000);
		WebElement selectDay = driver.findElement(By.xpath("//*[@id=\"um-datepicker\"]/div/div/div/div[2]/div[2]/div/div[3]/div/table/tbody/tr[1]/td[4]/div"));
		selectDay.click();
		Thread.sleep(1000);
		WebElement Message = driver.findElement(By.xpath("//*[@id=\"widget-modal\"]/div[3]/section/p"));
		
		Assert.assertEquals(Message.getText(), "Unfortunately it is not possible to book a table more than 1 month in advance");


	}
	
	// Test Closed days 
	@Test (priority = 2)
	public void CheckClosedDays () throws InterruptedException {
		
		driver.navigate().refresh();
		Thread.sleep(3000);
		WebElement ChangeCalendar = driver.findElement(By.xpath("//*[@id=\"um-reservation-date-picker\"]"));
		ChangeCalendar.click();
		Thread.sleep(1000);
		WebElement selectday = driver.findElement(By.xpath("//*[@id=\"um-datepicker\"]/div/div/div/div[2]/div[2]/div/div[2]/div/table/tbody/tr[1]/td[2]/div"));
		selectday.click();
		Thread.sleep(1000);
		WebElement Message = driver.findElement(By.xpath("//*[@id=\"widget-modal\"]/div[3]/section/div[2]/section[1]/h3"));
	    Assert.assertEquals(Message.getText(), "We are closed on this day");
	}
	// Not avaliable tables in this day 
	
	@Test (priority = 3)
	public void noTables () throws InterruptedException, AWTException {
		driver.navigate().refresh();
		Thread.sleep(3000);
		WebElement selectday = driver.findElement(By.xpath("//*[@id=\"um-tenDateSelector\"]/ul/li[10]/button"));
		selectday.click();
		Thread.sleep(1000);
		WebElement selectpartSize =driver.findElement(By.xpath("//*[@id=\"um-reservation-party-size\"]/div/div[1]"));
		selectpartSize.click();
		//Select option from Drop down List 
		Robot robot = new Robot();
		// Simulate key Events
		robot.keyPress(KeyEvent.VK_TAB);
		robot.keyRelease(KeyEvent.VK_TAB);
		Thread.sleep(1000);
		//Compare actual result with Expected Result 
		WebElement Message =driver.findElement(By.xpath("//*[@id=\"widget-modal\"]/div[3]/section/div[2]/section[1]/section/h4"));
		Assert.assertEquals(Message.getText(), "There are no available tables");
		
		
	}
	
	// Close browser after test
	
	@AfterTest
	public void afterTest() {
		driver.close();
	}
}
