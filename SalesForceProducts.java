package week4.day2;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.sukgu.Shadow;

public class SalesForceProducts {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ChromeOptions options = new ChromeOptions();
		options.addArguments("-disable-notifications");
		options.setHeadless(true);
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		//Launch URL
		
		driver.get("https://login.salesforce.com");
		driver.manage().window().maximize();
		
		//Login With UserID and Password
		driver.findElement(By.xpath("//input[@class='input r4 wide mb16 mt8 username']")).sendKeys("ramkumar.ramaiah@testleaf.com");
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("Password$123");
		driver.findElement(By.xpath("//input[@name='Login']")).click();
		//Click on Mobile Learn Button

		driver.findElement(By.xpath("//span[text()='Learn More']")).click();
		
		// Switch to second winodw and press confirm
		Set<String> windowhandle1 = driver.getWindowHandles();
		System.out.println(windowhandle1.size());
		List<String> windowhandle2= new ArrayList<String>(windowhandle1);
		String getsecondwindow= windowhandle2.get(1);
		driver.switchTo().window(getsecondwindow);
		driver.findElement(By.xpath("//button[text()='Confirm']")).click();
		// Use shadow function to click disappear elements while inspecting and click learning and Learning on Trailhead 
		Shadow dom=new Shadow(driver);
		dom.findElementByXPath("//span[text()='Products']").click();
dom.findElementByXPath("//span[text()='Service']").click();
		
		dom.findElementByXPath("//a[text()='Customer Service']").click();
		
		 // check if Navigated to customer service window
		System.out.println(driver.getTitle());
		
		//Verify the list of services available
		
		List<WebElement> services = driver.findElements(By.xpath("//ul[@class='page-list page-list-level-2']/li/a"));

		System.out.println("The number of services available are : " + services.size());

		System.out.println("=======================================================");
		for (WebElement webElement : services) {
			String name = webElement.getText();
			System.out.println(name);
			driver.getTitle();
		

}
}
}
