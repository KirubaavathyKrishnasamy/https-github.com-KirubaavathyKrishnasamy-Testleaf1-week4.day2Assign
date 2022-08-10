package week4.day2;




import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Nykaa {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
WebDriverManager.chromedriver().setup();
		
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		//Go to https://www.nykaa.com/
		driver.get("https://www.nykaa.com/");
		
		Actions builder = new Actions(driver);
		
		//Mouseover on Brands and Search L'Oreal Paris
		WebElement brandEle = driver.findElement(By.xpath("//a[text()='brands']"));
		builder.moveToElement(brandEle).perform();
		
		//Click L'OrealParis
		driver.findElement(By.xpath("//a[contains(text(),'Oreal Paris')]")).click();
		
		//Check the title contains L'Oreal Paris(Hint-GetTitle)
		String title = driver.getTitle();
		
		if(title.contains("L'Oreal Paris"))
		{
			System.out.println("Title contains L'Oreal Paris");
		}
		
		//Click sort By and select customer top rated
		driver.findElement(By.className("sort-name")).click();
		driver.findElement(By.xpath("(//span[text()='customer top rated']/following::div)[1]")).click();
						
		Thread.sleep(3000);
    
		//Click Category and click Hair->Click haircare->Shampoo
		driver.findElement(By.xpath("//span[text()='Category']")).click();
		driver.findElement(By.xpath("//span[text()='Hair']")).click();
		driver.findElement(By.xpath("//span[text()='Hair Care']")).click();
		driver.findElement(By.xpath("(//span[text()='Shampoo']/following::div)[1]")).click();
			 
		//Click->Concern->Color Protection
		driver.findElement(By.xpath("//span[text()='Concern']")).click();
		driver.findElement(By.xpath("(//span[text()='Color Protection']/following::div)[1]")).click();
		
		//check whether the Filter is applied with Shampoo
		List<WebElement> filters = driver.findElements(By.xpath("//span[@class='filter-value']"));
		
		for(WebElement fil:filters)
		{
			if(fil.getText().equalsIgnoreCase("Shampoo"))
			{
				System.out.println("Shampoo filter is applied.");
			}
		}
		
		//move to top of page
		WebElement topEle = driver.findElement(By.xpath("//h1[text()='All Products']"));
		
		builder.scrollToElement(topEle).perform();
		
		//Click on L'Oreal Paris Colour Protect Shampoo 	
		driver.findElement(By.xpath("//div[@class='css-xrzmfa']")).click();
	
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> lstWindow = new ArrayList<String>(windowHandles);
		
		//GO to the new window and select size as 175ml
		driver.switchTo().window(lstWindow.get(1));
		
		WebElement sizeDrop = driver.findElement(By.className("css-2t5nwu"));
		Select sel = new Select(sizeDrop);
		sel.selectByVisibleText("175ml");
		
	
		//Print the MRP of the product
		String price = driver.findElement(By.xpath("(//span[text()='MRP:']/following-sibling::span)[1]")).getText();
		System.out.println(price);
		
		//Click on ADD to BAG
		driver.findElement(By.xpath("(//button[@class=' css-12z4fj0']/span[text()='Add to Bag'])[1]")).click();
		
		//driver.close();
		
		//driver.switchTo().window(parent);
		
		//Go to Shopping Bag 
		driver.findElement(By.xpath("//button[@class='css-g4vs13']")).click();
		System.out.println("shopping bag");
		
		Thread.sleep(3000);
		
		//Print the Grand Total amount
		WebElement frameEle = driver.findElement(By.xpath("//div[@class='    css-1w65igk e1j92jav0']/iframe"));
		
		driver.switchTo().frame(frameEle);
		
		System.out.println("Switched to frame");
		
		String newTotal = driver.findElement(By.xpath("//div[@class='value medium-strong']")).getText();
		System.out.println(newTotal);
		
		//Click Proceed
		driver.findElement(By.xpath("//span[text()='Proceed']")).click();
		
		Set<String> windowHandles2 = driver.getWindowHandles();
		List<String> lstWin = new ArrayList<String>(windowHandles2);
		
		driver.switchTo().window(lstWin.get(1));
		
		//Click on Continue as Guest
		driver.findElement(By.xpath("//button[contains(text(),'CONTINUE AS GUEST')]")).click();
		
		//Check if this grand total is the same in step 14
		String totCheck = driver.findElement(By.xpath("//div[text()='Grand Total']/following::span")).getText();
		System.out.println(totCheck);
		
		if(newTotal.contains(totCheck))
		{
			System.out.println("Total is verified");
		}
		
		//Close all windows
		driver.quit();	
	}

}
