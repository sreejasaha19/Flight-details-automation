package pageObjects;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import userDefinedLibraries.BaseClass;

public class HomePage extends BasePage{
	Random random = new Random();
	
	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//*[@class='_actvrmenu'][1]")
	WebElement flight;

	@FindBy(xpath="//li[@id='rtrip']")
	WebElement roundtrip;

	@FindBy(xpath="//*[@id='frmcity']")
	WebElement fromtrip;

	@FindBy(xpath="(//*[@class='ausuggest'])[1]/li")
	List<WebElement> fromcity;

	@FindBy(xpath="(//*[@class='ausuggest'])[2]/li")
	List<WebElement> tocity;


	@FindBy(xpath="//*[(contains(@id, 'frth_'))or contains(@id, 'fiv_')or contains(@id, 'fst_')]")
	List<WebElement> DepartureDate;

	@FindBy(xpath="//*[@id='trvlr_colm']")
	WebElement travellerClass;

	@FindBy(xpath="(//button[@id='add'])[1]")
	WebElement adult;

	@FindBy(xpath="(//button[@id='add'])[2]")
	WebElement child;

	@FindBy(xpath="(//button[@id='add'])[3]")
	WebElement infant;

	@FindBy(xpath="//*[contains(@class, 'optCabin')]")
	List<WebElement> cabin;

	@FindBy(linkText="Done")
	WebElement DoneButton;

	@FindBy(xpath="//*[@class='srchBtnSe']")
	WebElement Search;

	
	public void clickFlightTab() {
		flight.click();
	}
	public void roundTrip() {
		roundtrip.click();
	}
	public void fromTrip() {
		fromtrip.click();
	}
	public void fromCity() throws InterruptedException {
	    for (WebElement option : fromcity) {
	        if (option.getText().contains((BaseClass.p.getProperty("FromCity")))) {
	        	option.click();
	        	break;
	        }
	    }
	}


	public void toCity() throws InterruptedException {
	    for (WebElement options : tocity) {
	        if (options.getText().contains(BaseClass.p.getProperty("ToCity"))) {
	        	options.click();
	        	break;
	        }
	    }
	}

	public void selectDepartureDate() {
	    int randomIndex = random.nextInt(DepartureDate.size());
	    DepartureDate.get(randomIndex).click();

	}
	public void selectReturnDate() {
	    int randomIndex = random.nextInt(DepartureDate.size());
	    DepartureDate.get(randomIndex).click();

	}

	public void selectTravellerClass(String text) throws Exception {
		BaseClass.scrollToElement(travellerClass);
		travellerClass.click();
		adult.click();
		child.click();
		infant.click();
		
		for (WebElement ele : cabin) {
	        if (ele.getText().equalsIgnoreCase(text)) {
	        	BaseClass.ClickElement(ele);
	        }
	    }

		
		Thread.sleep(1000);
		BaseClass.ClickElement(DoneButton);
		Search.click();
	}

}
