package pageObjects;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import userDefinedLibraries.BaseClass;
import utilities.ExcelUtility;

public class SpicejetPage extends BasePage{
	Random random = new Random();
    // Lists to store flight details
    List<String> flightNames = new ArrayList<>();
    List<String> departures = new ArrayList<>();
    List<String> arrivals = new ArrayList<>();
    List<String> prices = new ArrayList<>();


	public SpicejetPage(WebDriver driver) {
		super(driver);
	}


@FindBy(xpath="//*[@id='hidesec']//div[3]//div[@class='row_flt ng-scope']//div//label")
List<WebElement> FlightOption;
@FindBy(xpath="//*[contains(@class, 'ng-scope AC')]")
List<WebElement> flightsAvailable;

@FindBy(xpath="//*[contains(@class, 'ng-scope AC')]//div//div[2]//div[2]/span[2]")
List<WebElement> nameElements;

@FindBy(xpath="//*[contains(@class, 'ng-scope AC')]//div/div[3]/div[1][contains(@class, 'font20')]")
List<WebElement> departureElements;

@FindBy(xpath="//*[contains(@class, 'ng-scope AC')]//div/div[5]/div[1][contains(@class, 'font20')]")
List<WebElement> arrivalElements;

@FindBy(xpath="//*[contains(@class, 'ng-scope AC')]//div/div[6]/div/div[(contains(@val,'true') and (position()=2 or position()=3))]")
List<WebElement> priceElements;





public void filterIndigo(String name) {
		for (WebElement no : FlightOption) {
		    if (no.getText().equalsIgnoreCase(name)) {
		        if (no.isSelected()) {
		            no.click();
		        }
		    } else {
		        if (!no.isSelected()) {
		            no.click();
		        }
		    }
		}
}
		
public void fetchDetails() {
	System.out.println("No Of Indigo flights available :" +flightsAvailable.size() + "\n");
        for (WebElement nameElement : nameElements) {
        	BaseClass.scrollToElement(nameElement);
        	String flightName = nameElement.getText();
            flightNames.add(flightName);
        }
        for (WebElement departureElement : departureElements) {
            String departure = departureElement.getText();
            departures.add(departure);
        }            
        for (WebElement arrivalElement : arrivalElements) {
            String arrival = arrivalElement.getText();
            arrivals.add(arrival);
        }
        for (WebElement priceElement : priceElements) {
            String price = priceElement.getText();
            prices.add(price);
        }
        
	
//        System.out.println("Departures: " + departures);
//        System.out.println("Arrivals: " + arrivals);
//        System.out.println("Prices: " + prices);
//        System.out.println("Flight Names: " + flightNames);


	
    }
    
    
//    }

//to store data in excel sheet
	public void printFlightDetails() {
	    List<String> headers = Arrays.asList("Flight Name", "Departure", "Arrival", "Price");

	    
	    try {
	    	ExcelUtility.putData(flightNames, 0, "Flight", headers);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    try {
	    	ExcelUtility.putData(departures, 1, "Flight", headers);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    try {
	        ExcelUtility.putData(arrivals, 2, "Flight", headers);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    try {
	    	ExcelUtility.putData(prices, 3, "Flight", headers);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}




	
	
	

}
