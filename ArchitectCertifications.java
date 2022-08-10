package week4.day2;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.sukgu.Shadow;

public class ArchitectCertifications {

	public static void main(String[] args) {
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
				
				// Switch to second winodw and press confirm
				Set<String> windowhandle1 = driver.getWindowHandles();
				System.out.println(windowhandle1.size());
				List<String> windowhandle2= new ArrayList<String>(windowhandle1);
				String getsecondwindow= windowhandle2.get(1);
				driver.switchTo().window(getsecondwindow);
				driver.findElement(By.xpath("//button[text()='Confirm']")).click();
				// Use shadow function to click disappear elements while inspecting and click learning and Learning on Trailhead 
				Shadow dom=new Shadow(driver);
				dom.findElementByXPath("//span[text()='Learning']").click();
				dom.findElementByXPath("//span[text()='Learning on Trailhead']").click();
				// Use Actions for MouseOver
				Actions action = new Actions(driver);
				WebElement trailHead = dom.findElementByXPath("//span[text()='Learning on Trailhead']");
				action.moveToElement(trailHead);
				trailHead.click();
				// Use Explicit Wait
				WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(2000));
				WebElement visible =dom.findElementByXPath("//a[text()='Salesforce Certification']");
				wait.until(ExpectedConditions.visibilityOf(visible)).click();
				// Find Architect certification counts and print the same
				driver.findElement(By.xpath("//img[@alt='Salesforce<br/>Architect']")).click();
				String text = driver.findElement(By.xpath("//div[@class='cert-site_text slds-text-align--center Lh(1.5) Fz(16px) slds-container--center slds-p-bottom--large']")).getText();
				System.out.println(text);
				List<WebElement> certifications = driver.findElements(By.xpath("//div[@class = 'credentials-card_title']"));
				System.out.println(certifications.size());
				driver.findElement(By.xpath("//a[text()='Application Architect']")).click();
				String text1 = driver.findElement(By.xpath("//a[text() = 'Data Architect']")).getText();
				System.out.println(text1);
				String text2 = driver.findElement(By.xpath("//a[text() = 'Sharing and Visibility Architect'][1]")).getText();
				System.out.println(text2);
				String text3 = driver.findElement(By.xpath("//a[text() = 'Platform Developer I']")).getText();
				System.out.println(text3);
				String text4 = driver.findElement(By.xpath("//a[text() = 'Platform App Builder']")).getText();
				System.out.println(text4);
				
	

}
}
