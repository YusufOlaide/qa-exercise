package com.org.rise.capital.steps;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import com.org.rise.capital.pom.pages.HomePage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Managed;
import utils.CP;


public class BuildWealthStepDefinition {
	
	HomePage homePage;
	CP property = new CP();
	@Managed                                                            
    WebDriver driver;
	
	@Given("^User is on Rise Capital home page$")
	public void userIsOnRiseCapitalHomePager() throws IOException {
		homePage= new HomePage(driver);
		homePage.navigateToHomePage();
	}
	
	@And("^Product menu is present$")
	public void productMenuIsPresent() throws IOException {
		String pr = homePage.verifyProduct();
		Assert.assertEquals(pr, property.getObjectRepository().getProperty("pr"));
	}
	
	@When("^User clicks on the product menu$")
	public void userClicksOnProductMenu() {
		homePage.clickOnProductMenu();
	}
	
	@Then("^Build Wealth option is included in the submenu list$")
	public void verifyBuildWealthOption() throws IOException {	
		String buildWealth = homePage.verifyBuildWealth();
		Assert.assertEquals(buildWealth, property.getObjectRepository().getProperty("bw"));
	}
	
	@And("^User clicks on the Build Wealth Option$")
	public void User_clicks_on_the_Build_Wealth_Option() {
		homePage.clickOnBuildWealth();
	}
	
	@And("^User validates all texts in H1 tag present$")
	public void User_validates_all_texts_in_H1_tag_present() {
		homePage.printTags();
		homePage.tearDown();
	}
}
