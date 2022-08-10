package week4.day2;

import java.time.Duration;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;


import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.sukgu.Shadow;

public class AdminstrationCertifications {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		//Setup ChromeDriver
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
		
		//Switch to zecond window
		Set<String> windowhandle1 = driver.getWindowHandles();
		System.out.println(windowhandle1.size());
		List<String> windowhandle2= new ArrayList<String>(windowhandle1);
		String getsecondwindow= windowhandle2.get(1);
		driver.switchTo().window(getsecondwindow);
		// Press confirm on second window
		driver.findElement(By.xpath("//button[text()='Confirm']")).click();
		// Use shadow function to click disappear elements while inspecting and click learning and Learning on Trailhead 
		Shadow dom=new Shadow(driver);
		dom.findElementByXPath("//span[text()='Learning']").click();
		dom.findElementByXPath("//span[text()='Learning on Trailhead']").click();
		// Use Mouse Over funcation
		Actions action = new Actions(driver);
		WebElement trailHead = dom.findElementByXPath("//span[text()='Learning on Trailhead']");
		action.moveToElement(trailHead);
		trailHead.click();
		
	// Use Explicit wait function
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(2000));
		WebElement visible =dom.findElementByXPath("//a[text()='Salesforce Certification']");
		wait.until(ExpectedConditions.visibilityOf(visible)).click();
	// Click Administration and check list of Certification and print the same
		driver.findElement(By.linkText("Administrator")).click();
		//get the certifications list			
		List<WebElement> certifications = driver.findElements(By.xpath("(//div[@class='trailMix-card-body']/div[2])/a"));
		System.out.println(certifications.size());
		String text = driver.findElement(By.xpath("//a[text()='Prepare for Your Salesforce Administrator Credential']")).getText();
		System.out.println(text);
		String text1 = driver.findElement(By.xpath("//a[text()='Study for the Administrator Certification Exam']")).getText();
		System.out.println(text1);
		String text2 = driver.findElement(By.xpath("//a[text()='Interactive Practice Test for the Salesforce Certified Administrator Exam']")).getText();
		System.out.println(text2);
		
		
		
			
	}

}


