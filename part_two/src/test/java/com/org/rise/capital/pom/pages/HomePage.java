package com.org.rise.capital.pom.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import utils.CP;

public class HomePage {
	
	WebDriver driver;

	By titleText = By.xpath("//head//title");
	By product = By.xpath("//span[contains(text(),'Products')]");
	By buildWealth = By.xpath("//a[normalize-space()='Build Wealth']");

	
	public HomePage(WebDriver driver) {
		CP property = new CP();
		String browser = property.getObjectRepository().getProperty("browser");
		switch (browser){
        case "Chrome":
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            break;
        case "Firefox":
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
            break;
        case "Edge":
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
            break;
        default:
            throw new IllegalStateException("Invalid browser name: "+ browser);
    }
		driver.manage().window().maximize();
		this.driver = driver;
		
	}
	
	public void tearDown() {
		driver.quit();
	}
	
	public void navigateToHomePage() {
		driver.get("https://rise.capital/");
		driver.manage().window().maximize();
	}
	
	public String homePageTitle() {
		return driver.findElement(titleText).getText();
	}
	
	public String verifyProduct() {
		driver.findElement(product).isDisplayed();
		return driver.findElement(product).getText();
	}
	
	public void clickOnProductMenu() {
		driver.findElement(product).click();
	}
	
	public String verifyBuildWealth() {
		driver.findElement(buildWealth).isDisplayed();
		System.out.println(driver.findElement(buildWealth).getText());
		return driver.findElement(buildWealth).getText();
	}
	
	public void clickOnBuildWealth() {
		driver.findElement(buildWealth).click();
	}
	
	public List<String> printTags() {
		List<WebElement> elements = driver.findElements(By.tagName("h1"));
		List<String> tags = new ArrayList<>();
		for(int i = 0; i<elements.size();i++) {
		    System.out.println(elements.get(i).getText());
		    tags.add(elements.get(i).getText());
		}
		
		return tags; 
	}


}
