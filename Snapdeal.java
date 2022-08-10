package week4.day2;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Snapdeal {

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		//Launch https://www.snapdeal.com/
		driver.get("https://www.snapdeal.com/");
		
		Actions builder = new Actions(driver);
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		//Go to Mens Fashion
		WebElement menElement = driver.findElement(By.xpath("(//span[contains(text(),'Men')])[1]"));
		
		builder.moveToElement(menElement).perform();
		
		//Go to Sports Shoes
		driver.findElement(By.xpath("//span[text()='Sports Shoes']")).click();
		
		//Get the count of the sports shoes
		String countVal = driver.findElement(By.xpath("//span[@class='category-name category-count']")).getText();
		String total=countVal.replaceAll("[^0-9]","");
		
		//Click Training shoes
		driver.findElement(By.xpath("//div[text()='Training Shoes']")).click();
		
		Thread.sleep(1000);
		
		//Sort by Low to High
		driver.findElement(By.xpath("(//span[text()='Sort by:']/following::i)[1]")).click();
		
		driver.findElement(By.xpath("(//li[@data-index='1'])[2]")).click();
		
		//Check if the items displayed are sorted correctly
		//Select the price range (900-1200)
		WebElement priceEle = driver.findElement(By.xpath("//div[@class='filter-type-name lfloat']"));
		builder.scrollToElement(priceEle).perform();
		
		driver.findElement(By.xpath("//input[@name='fromVal']")).clear();
		driver.findElement(By.xpath("//input[@name='fromVal']")).sendKeys("900");
		
		driver.findElement(By.xpath("//input[@name='toVal']")).clear();
		driver.findElement(By.xpath("//input[@name='toVal']")).sendKeys("1200");
		driver.findElement(By.xpath("//div[contains(text(),'GO')]")).click();
		
		Thread.sleep(3000);
		//Filter with color Navy 
		WebElement colorEle = driver.findElement(By.xpath("(//div[@class='filter-type-name lfloat'])[4]"));
		builder.scrollToElement(colorEle).perform();
		
		driver.findElement(By.xpath("(//div[@class='sdCheckbox filters-list '])[5]")).click();
		
		//verify the all applied filters
		List<WebElement> filters = driver.findElements(By.xpath("//div[@class='navFiltersPill']/a[@class='clear-filter-pill']"));
		
		System.out.println("Filters applied");
		for(WebElement fil:filters)
		{
			System.out.println(fil.getText());
		}
		
		Thread.sleep(1000);
		WebElement ele = driver.findElement(By.xpath("//div[@data-js-pos='4']"));
		builder.scrollToElement(ele).perform();
		
		//Mouse Hover on first resulting Training shoes
		WebElement firstItem = driver.findElement(By.xpath("(//img[@class='product-image wooble'])[1]"));
		builder.moveToElement(firstItem).perform();
		
		//click QuickView button
		driver.findElement(By.xpath("(//div[contains(text(),'Quick View')])[1]")).click();
		
		//Print the cost and the discount percentage
		String priceText = driver.findElement(By.xpath("(//div[@class='product-price pdp-e-i-PAY-l clearfix']/span)[1]")).getText();
		
		String discText = driver.findElement(By.xpath("(//div[@class='product-price pdp-e-i-PAY-l clearfix']/span)[2]")).getText();
		System.out.println("Cost is "+priceText+"\nDiscount is "+discText);
		
		Thread.sleep(2000);
		//Take the snapshot of the shoes.
		File source = driver.getScreenshotAs(org.openqa.selenium.OutputType.FILE);
		File dest = new File("./snap/snapdeal.png");
		FileUtils.copyFile(source, dest);
		
		//close the current window
		driver.quit();
	}

}