//Test Images > Images has Broken Images > Issue 
package Task.Automation;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class BrokenImages {
	ChromeDriver driver;
	private int brokenimage;


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
    @Test(priority = 1)
      public void testBrokenImage() throws InterruptedException {
	//Select Partysize 7 people
    
	WebElement selectP = driver.findElement(By.xpath("//*[@id=\"um-reservation-party-size\"]/div/div[1]"));
    selectP.click();
	Thread.sleep(1000);
	WebElement option = driver.findElement(By.xpath("//*[@id=\"um-reservation-party-size\"]/div/div[1]/div"));
	Actions builder = new Actions(driver);
	builder.moveToElement(option,0,350).click().perform();
	Thread.sleep(1000);
	
	//Select date 
	WebElement selectDay =driver.findElement(By.xpath("//*[@id=\"um-tenDateSelector\"]/ul/li[4]/button"));
	selectDay.click();
	Thread.sleep(2000);
	//Select Time slot 
    WebElement selectTimeSlot =driver.findElement(By.xpath("//*[@id=\"timeslot----2\"]"));
    selectTimeSlot.click();
	Thread.sleep(1000);
	//Select indoor option 
    WebElement indoorOpt =driver.findElement(By.xpath("//*[@id=\"widget-modal\"]/div[3]/section/div[2]/section[1]/section[1]/div/div[3]/div/nav/button[2]"));
    indoorOpt.click();
	Thread.sleep(1000);

}
    @Test (priority = 2)
	public void Test01 () {

    brokenimage= 0; 
	//find List of images 
	java.util.List<WebElement> image_list = driver.findElements(By.tagName("img"));
	System.out.println("The page under test has " + image_list.size() + " images");
	
// for loop to check broken images 
	
	for (WebElement img :image_list) {
		if (img !=null ) {
			test02(img);
		}
	}
	//broken image print
	System.out.println("total number of brokenImages are"+brokenimage);
}
	public void test02 (WebElement img) {
		
		
		//create new instance of httpclient 
		HttpClient client = HttpClientBuilder.create().build();
		//Create a new instance of HttpGet
		HttpGet request = new HttpGet(img.getAttribute("src"));
		//Retrieve the response object
		try {
			HttpResponse  response = client.execute(request);
			if ( response.getStatusLine().getStatusCode() !=200) {
				brokenimage++;
				
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
}}