package com.org.rise.capital.pom.pages;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import utils.CP;

public class BuildWealthPage {
	
	WebDriver driver;
	CP property = new CP();
	
	By titleText = By.xpath("//head//title");
	By age = By.xpath("//input[@name='age']");
	By earnings = By.xpath("//input[@name='salary']");
	By percentage = By.xpath("//input[@name='investmentPercentage']");
	By retirementAge = By.xpath("//input[@name='retirementAge']");
	By dropdown = By.xpath("//select[@id='investmentPreference']");
	By stability = By.xpath("//option[@value='0.5']");
	By submitBtn = By.xpath("//button[normalize-space()='Calculate']");
	By h1 = By.tagName("h1");
	By wealthValue = By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/p[2]");
	
	public BuildWealthPage(WebDriver driver) {
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
	
	public String BuildWealthPageTitle() {
		return driver.findElement(titleText).getText();
	}
	
	public void navigateToBuildWealthPage() {
		driver.get("https://risevest.com/build-wealth");
		driver.manage().window().maximize();
	}
	
	public void enterAge() throws IOException {
		driver.findElement(age).clear();
		driver.findElement(age).sendKeys(property.getObjectRepository().getProperty("age"));
	}
	
	public void enterEarnings() throws IOException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,750)");
		driver.findElement(earnings).clear();
		driver.findElement(earnings).sendKeys(property.getObjectRepository().getProperty("salary"));
	}
	
	public void enterPercentage() throws IOException {
		driver.findElement(percentage).clear();
		driver.findElement(percentage).sendKeys(property.getObjectRepository().getProperty("percentage"));
	}
	
	public void enterRetirementAge() throws IOException {
		driver.findElement(retirementAge).clear();
		driver.findElement(retirementAge).sendKeys(property.getObjectRepository().getProperty("retirementAge"));
	}
	
	public void clickOnDropdown() {
		WebElement drp = driver.findElement(dropdown);
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].click()", drp);
	}
	
	public void selectInvestmentPreference() {
		driver.findElement(stability).click();
	}
	
	public void clickOnCalculate() {
		WebElement calc = driver.findElement(submitBtn);
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].click()", calc);
	}
	
	public String verifyWealthValue() {
	JavascriptExecutor js = (JavascriptExecutor) driver;
	js.executeScript("window.scrollBy(0,-750)");
	driver.findElement(wealthValue).isDisplayed();
	System.out.println("Tope wealth's value at the age of 60 would be " + driver.findElement(wealthValue).getText());
	return driver.findElement(wealthValue).getText();
}


}
