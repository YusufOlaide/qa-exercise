package com.org.rise.capital.steps;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import com.org.rise.capital.pom.pages.BuildWealthPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Managed;


public class WealthFormCalculationStepDefinition {
	
	BuildWealthPage BWPage;
	@Managed                                                            
    WebDriver driver;
	
	
	@Given("^User is on the Build Wealth Page$")
	public void userIsOnTheBuildWealthPage(){
		BWPage = new BuildWealthPage(driver);
		BWPage.navigateToBuildWealthPage();
	}
	
	@When("^User enters the required form parameters$")
	public void userEntersFormParameters() throws IOException, InterruptedException {
		BWPage.enterAge();
		BWPage.enterEarnings();
		BWPage.enterPercentage();
		BWPage.enterRetirementAge();
		BWPage.clickOnDropdown();
		BWPage.selectInvestmentPreference();
	}
	
	@And("^User clicks on the calculate button$")
	public void userClicksOnCalculateButton() {
		BWPage.clickOnCalculate();
	}
	
	@Then("^Wealth Goal Projection is populated$")
	public void wealthGoalProjectionIsPopulated() throws IOException {	
		BWPage.verifyWealthValue();
		BWPage.tearDown();
	}

}
