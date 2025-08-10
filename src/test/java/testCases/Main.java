package testCases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.SpicejetPage;
import userDefinedLibraries.BaseClass;

public class Main {
	WebDriver driver;
	
	SpicejetPage sp;
	HomePage hp;
	public Logger logger=LogManager.getLogger(this.getClass());
	@BeforeClass
	@Parameters({"browser"})
	void setUp(String browsertype) throws Exception {
		logger.info("**** Starting Test Cases ******");
		driver=BaseClass.driverInstantiate(browsertype);
		sp=new SpicejetPage(driver);
		hp=new HomePage(driver);
		
	}
	@Test(priority=1)
	void selectFromCity() throws InterruptedException {
		hp.clickFlightTab();
		hp.roundTrip();
		hp.fromTrip();
		hp.fromCity();
		hp.toCity();
	}
	
	@Test(priority=2)
	void selectToCity() throws InterruptedException {
		hp.toCity();
	}
	
	@Test(priority=3)
	void selectDate() throws InterruptedException {
		hp.selectDepartureDate();
		Thread.sleep(1000);
		hp.selectReturnDate();
		Thread.sleep(1000);
	}
	@Test(priority=4)
	void selectclass() throws Exception {
		hp.selectTravellerClass("economy");
	}
	
	@Test(priority=5)
	void selectIndigo() {
		sp.filterIndigo("Indigo");
		
	}
	@Test(priority=6)
	void indigoDetails() {
		sp.fetchDetails();
		
	}
	@Test(priority=7,dependsOnMethods= {"indigoDetails","selectIndigo"})
	void printFlightdetails() {
		sp.printFlightDetails();
		
	}
	
	
	
	@AfterClass
	void tearDown() {
		// Tearing down the WebDriver
		BaseClass.driverTearDown();
		logger.info("**** Ending Test Cases ******");
	}
		
	
	


}
