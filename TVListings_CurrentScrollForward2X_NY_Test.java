package Tests;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import PageObjects.CurrentDateHour;
import PageObjects.TVListingsPage;
import Setup.TestBase;
import otherClasses.CurrentDateTime;
import otherClasses.GetDatePrintout;
import otherClasses.ScreenshotURL;

public class TVListings_CurrentScrollForward2X_NY_Test extends TestBase {
	
	static String zipcode = "10002";
	static String timezone = "US/Eastern";
	static SoftAssert softAssert = new SoftAssert();
	
	@Test
	public void VerifyCurrentScrollForward2XNYPage() throws InterruptedException, IOException
	{

		String className = this.getClass().getSimpleName();
	 	Date date1= new Date();
	 	String originaltimestamp = new Timestamp(date1.getTime()).toString();
	 	String timestamp = originaltimestamp.replace(':', 'x').substring(11);
		String foldername = folderpath+className+timestamp;
		String error = "";
		String errorname = "";
		
		// Create instance of Javascript executor
		JavascriptExecutor je = (JavascriptExecutor) driver;
		
		TVListingsPage tvlist = new TVListingsPage();
		//Execute query which will scroll until the element appears on page.
		je.executeScript("arguments[0].scrollIntoView(true);",tvlist.FullTVListings()); 
	    tvlist.FullTVListings().click();
	    Thread.sleep(5000);
	    
	    //Change zipcode
	    tvlist.Zipcode().click();
	    tvlist.ZipcodeField().clear();
	    tvlist.ZipcodeField().sendKeys(zipcode);
	    Thread.sleep(2000);
	    
	    //Choose tv provider
	    List <WebElement> options = tvlist.TVProviderOptions();
	    for (WebElement option : options) 
	    {
	    	if ("Time Warner Southern Manhattan (New York)".equals(option.getText()))
    			option.click();
        		Thread.sleep(2000);
	    }
	    
	    //Click save zipcode changes
	    tvlist.SaveZipcode().click();
	    Thread.sleep(5000);
	    
	    //Need this section to get current time from your time zone
	    String region = tvlist.RegionSetting().getText();
		System.out.println("region: "+region);
		
		String currenttime = tvlist.GetCurrentTimeUsingRegion(region);
		
		
		//After refresh of the page, the element the webdriver references will now contain a stale reference. 
		//As the page has changed, the element will no longer be where webdriver expects it to be.
	    //Check if selected date in date bar matches todays date
	    CurrentDateHour curdatehr = new CurrentDateHour();
		String selectedshortdate = curdatehr.EasternXpathPhrase().getText();
	    selectedshortdate = selectedshortdate.replaceAll("[\n\r]", "");
	    String weekday = CurrentDateTime.easternweekday();
	    String shortdate = weekday + CurrentDateTime.easternday();

	    try
	    {
	    	Assert.assertEquals(selectedshortdate, shortdate, "The selected first date do not match current date."); //Assert.assertEquals(actual, expected)
	    }
		catch(AssertionError e)
		{ 
			error = e.toString();
			System.out.println(error);
			errorname = "firstselecteddate";
			ScreenshotURL.screenshotURL(driver, foldername, errorname, error);
			softAssert.fail();
		}
	    
	    
		
		//Check time is current time
        System.out.println("Current Time :"+currenttime);
        String listingscurrenttime = tvlist.AHr().getText();
        System.out.println("Listings Current Time :"+listingscurrenttime);
        
        try
        {
        	Assert.assertEquals(listingscurrenttime, currenttime, "TV Listings page current time do not match current time.");
        }
		catch(AssertionError e)
		{ 
			error = e.toString();
			System.out.println(error);
			errorname = "currenttime";
			ScreenshotURL.screenshotURL(driver, foldername, errorname, error);
			softAssert.fail();
		} 
        
        
        //Scroll Forward
        tvlist.ClickForward1().click();
        Thread.sleep(5000);
        
		//Find new scrolled forward time bar hours

        //Check scrolled forward hours are correct
	    String newstartinghr1=CurrentDateTime.plus2hr(currenttime);
	    System.out.println("newstartinghr1 :"+newstartinghr1);
	    String scrollforward1_next1hr = CurrentDateTime.plus1hr(newstartinghr1);
	    String scrollforward1_next2hr = CurrentDateTime.plus2hr(newstartinghr1);
	    System.out.println("scrollforward1_next1hr :"+scrollforward1_next1hr);
	    System.out.println("scrollforward1_next2hr :"+scrollforward1_next2hr);
    

	    String scrollforwardnextAhr = tvlist.AHr().getText();
	    System.out.println("scrollforwardnextAhr: "+scrollforwardnextAhr);
	    
	    try
	    {
	    	Assert.assertEquals(scrollforwardnextAhr, newstartinghr1, "Scroll forward first hour is incorrect."); //Assert.assertEquals(actual, expected)
	    }
		catch(AssertionError e)
		{ 
			error = e.toString();
			System.out.println(error);
			errorname = "scrollforwardnextAhr";
			ScreenshotURL.screenshotURL(driver, foldername, errorname, error);
			softAssert.fail();
		} 
	    
	    
	    String scrollforwardnextBhr = tvlist.BHr().getText();
	    System.out.println("scrollforwardnextBhr: "+scrollforwardnextBhr);

	    try
	    {
	    	Assert.assertEquals(scrollforwardnextBhr, scrollforward1_next1hr, "Scroll forward 2nd hour is incorrect."); //Assert.assertEquals(actual, expected)
	    }
		catch(AssertionError e)
		{ 
			error = e.toString();
			System.out.println(error);
			errorname = "scrollforwardnextBhr";
			ScreenshotURL.screenshotURL(driver, foldername, errorname, error);
			softAssert.fail();
		} 
	    

	    String scrollforwardnextChr = tvlist.CHr().getText();
	    System.out.println("scrollforwardnextChr: "+scrollforwardnextChr);
	    
	    try
	    {
	    	Assert.assertEquals(scrollforwardnextChr, scrollforward1_next2hr, "Scroll forward 3rd hour is incorrect."); //Assert.assertEquals(actual, expected)
	    }
		catch(AssertionError e)
		{ 
			error = e.toString();
			System.out.println(error);
			errorname = "scrollforwardnextChr";
			ScreenshotURL.screenshotURL(driver, foldername, errorname, error);
			softAssert.fail();
		} 
    
	    
		//When scroll forward only change the date to the next day if it is 12am, 1am, 2am or 3am. Has to be lowercase am since your function is in lowercase am. Other cases check if selected date in date bar matches today's date
	    selectedshortdate = tvlist.SelectedDate().getText();
	    selectedshortdate = selectedshortdate.replaceAll("[\n\r]", "");
	    
	    System.out.println("Selected date: "+ selectedshortdate);
	    System.out.println("newstartinghr1 :"+newstartinghr1);
	    
	    String [] nextdayhours = {"12:00am", "1:00am"};
	    
				      //scrollforward1X //scrollforward2X
		//current day    //next day     //next day
		//8pm 9pm 10pm   10pm 11pm 12am 12am 1am 2am
		//9pm 10pm 11pm  11pm 12am 1am  1am 2am 3am
		//10pm 11pm 12am 12am 1am 2am   2am 3am 4am
		//11pm 12am 1am  1am 2am 3am    3am 4am 5am
		//12am 1am 2am 	 2am 3am 4am	4am 5am 6am

	    
	    //Check if newstartinghr1 falls into the list of hours that will change into the next day
	    if (Arrays.asList(nextdayhours).contains(newstartinghr1))
	    {
		    weekday = CurrentDateTime.easternweekday();
		    
		    //add one day from today
		    switch (weekday)
		    {
	    		case "SUN":
	    			weekday = "MON";
	    			break;
		    	case "MON":
			    	weekday = "TUE";
			    	break;
			    case "TUE":
			        weekday = "WED";
			        break;
	    		case "WED":
	    			weekday = "THU";
	    			break;
		    	case "THU":
			    	weekday = "FRI";
			    	break;
			    case "FRI":
			        weekday = "SAT";
			        break;
			    case "SAT":
			        weekday = "SUN";
			        break;  
		    }
		    

		    LocalDate date = LocalDate.now(ZoneId.of(timezone));
			date = date.plusDays(1);
			int nextday=date.getDayOfMonth();
			String nextDate = Integer.toString(nextday);
            nextDate = weekday+nextDate;
            
            System.out.println("nextDate calculated: "+nextDate);

            try
            {
            	Assert.assertEquals(selectedshortdate, nextDate, "Scroll forward 1 selected date is incorrect."); //Assert.assertEquals(actual, expected)
            }
			catch(AssertionError e)
			{ 
				error = e.toString();
				System.out.println(error);
				errorname = "scrollforward1selecteddate";
				ScreenshotURL.screenshotURL(driver, foldername, errorname, error);
				softAssert.fail();
			} 
            
            
            //Assert date print out is updated to next day
		    String selecteddateprintout1 = tvlist.DatePrintout().getText();
		    
		    String calculateddateprintout1 = GetDatePrintout.newdateprintout(1, timezone);
		    
		    try
		    {
		    	Assert.assertEquals(selecteddateprintout1, calculateddateprintout1, "Scroll forward 1 into next day date printout is incorrect."); //Assert.assertEquals(actual,expected)
		    }
		    catch (AssertionError e)
		    {
				error = e.toString();
				System.out.println(error);
				errorname = "scrollforward1intonextdaydateprintout";
				ScreenshotURL.screenshotURL(driver, foldername, errorname, error);
				softAssert.fail();
		    }

	    }
	    else
	    {
		    weekday = CurrentDateTime.easternweekday();
		    shortdate = weekday + CurrentDateTime.easternday();
		    
		    try
		    {
		    	Assert.assertEquals(selectedshortdate, shortdate, "Scroll forward 1 selected date is incorrect."); //Assert.assertEquals(actual, expected)
		    }
			catch(AssertionError e)
			{ 
				error = e.toString();
				System.out.println(error);
				errorname = "scrollforward1selecteddate";
				ScreenshotURL.screenshotURL(driver, foldername, errorname, error);
				softAssert.fail();
			} 
		    
		    
		   //Assert the date printout is today's date
			try
			{
				//Check date printout is current date
				String listingscurrentdate1 = tvlist.DatePrintout().getText();
				System.out.println("TV listings date: " + listingscurrentdate1);
				String currentdate1 = GetDatePrintout.currentdate(timezone);

				Assert.assertTrue(currentdate1.equalsIgnoreCase(listingscurrentdate1), "Scroll Forward 1 Date printout do not match current date.");
			}
			catch (AssertionError e)
			{
				error = e.toString();
				System.out.println(error);
				errorname = "scrollforward1dateprintout";
				ScreenshotURL.screenshotURL(driver, foldername, errorname, error);
				softAssert.fail();
			}
            
		    	
	    }
	    
	    
	    //Scroll Forward 2X
        tvlist.ClickForward1().click();
        Thread.sleep(5000);

		//Find new scrolled forward time bar hours

        //Check scrolled forward hours are correct
	    newstartinghr1=scrollforward1_next2hr;
	    System.out.println("newstartinghr1 :"+newstartinghr1);
	    String scrollforward2_next1hr = CurrentDateTime.plus1hr(newstartinghr1);
	    String scrollforward2_next2hr = CurrentDateTime.plus2hr(newstartinghr1);
	    System.out.println("scrollforward2_next1hr :"+scrollforward2_next1hr);
	    System.out.println("scrollforward2_next2hr :"+scrollforward2_next2hr);
    
	    String scrollforwardnext2Ahr = tvlist.AHr().getText();
	    
	    System.out.println("scrollforwardnext2Ahr: "+scrollforwardnext2Ahr);
	    
	    try
	    {
		    Assert.assertEquals(scrollforwardnext2Ahr, newstartinghr1, "2nd Scroll forward first hour is incorrect."); //Assert.assertEquals(actual, expected)
		}
	    catch(AssertionError e)
		{ 
				error = e.toString();
				System.out.println(error);
				errorname = "scrollforwardnext2Ahr";
				ScreenshotURL.screenshotURL(driver, foldername, errorname, error);
				softAssert.fail();
		} 
	    

	    String scrollforwardnext2Bhr = tvlist.BHr().getText();
	    
	    System.out.println("scrollforwardnext2Bhr: "+scrollforwardnext2Bhr);
	    
	    try
	    {
	    	Assert.assertEquals(scrollforwardnext2Bhr, scrollforward2_next1hr, "2nd Scroll forward 2nd hour is incorrect."); //Assert.assertEquals(actual, expected)
	    }
	    catch(AssertionError e)
		{ 
				error = e.toString();
				System.out.println(error);
				errorname = "scrollforwardnext2Bhr";
				ScreenshotURL.screenshotURL(driver, foldername, errorname, error);
				softAssert.fail();
		}
	    
	    
	    String scrollforwardnext2Chr = tvlist.CHr().getText();

	    System.out.println("scrollforwardnext2Chr: "+scrollforwardnext2Chr);
	    
	    try
	    {
	    	Assert.assertEquals(scrollforwardnext2Chr, scrollforward2_next2hr, "2nd Scroll forward 3rd hour is incorrect."); //Assert.assertEquals(actual, expected)
	    }
	    catch(AssertionError e)
		{ 
				error = e.toString();
				System.out.println(error);
				errorname = "scrollforwardnext2Chr";
				ScreenshotURL.screenshotURL(driver, foldername, errorname, error);
				softAssert.fail();
		}

    
	   //When scroll forward only change the date to the next day if it is 12am, 1am, 2am or 3am. Has to be lowercase am since your function is in lowercase am. Other cases check if selected date in date bar matches today's date
	    selectedshortdate = tvlist.SelectedDate().getText();
	    selectedshortdate = selectedshortdate.replaceAll("[\n\r]", "");
	   
	    System.out.println("Selected date: "+selectedshortdate);
	    System.out.println("2nd forward newstartinghr1 :"+newstartinghr1);
	    
	    String [] nextdayhours2 = {"12:00am", "1:00am", "2:00am", "3:00am"};
	    
				      //scrollforward1X //scrollforward2X
		//current day    //next day     //next day
		//8pm 9pm 10pm   10pm 11pm 12am 12am 1am 2am
		//9pm 10pm 11pm  11pm 12am 1am  1am 2am 3am
		//10pm 11pm 12am 12am 1am 2am   2am 3am 4am
		//11pm 12am 1am  1am 2am 3am    3am 4am 5am
		//12am 1am 2am 	 2am 3am 4am	4am 5am 6am

	    
	    //Check if newstartinghr1 falls into the list of hours that will change into the next day
	    if (Arrays.asList(nextdayhours2).contains(newstartinghr1))
	    {
		    weekday = CurrentDateTime.easternweekday();
		    
		    //add one day from today
		    switch (weekday)
		    {
	    		case "SUN":
	    			weekday = "MON";
	    			break;
		    	case "MON":
			    	weekday = "TUE";
			    	break;
			    case "TUE":
			        weekday = "WED";
			        break;
	    		case "WED":
	    			weekday = "THU";
	    			break;
		    	case "THU":
			    	weekday = "FRI";
			    	break;
			    case "FRI":
			        weekday = "SAT";
			        break;
			    case "SAT":
			        weekday = "SUN";
			        break;  
		    }
		    

		    LocalDate date = LocalDate.now(ZoneId.of(timezone));
			date = date.plusDays(1);
			int nextday=date.getDayOfMonth();
			String nextDate = Integer.toString(nextday);
            nextDate = weekday+nextDate;
            
            System.out.println("nextDate calculated: "+nextDate);
            
            try
            {
            	Assert.assertEquals(selectedshortdate, nextDate, "2nd Scroll forward selected date is incorrect."); //Assert.assertEquals(actual, expected)
            }
			catch(AssertionError e)
			{ 
				error = e.toString();
				System.out.println(error);
				errorname = "scrollforward2selecteddate";
				ScreenshotURL.screenshotURL(driver, foldername, errorname, error);
				softAssert.fail();
			} 
            
            //Assert date print out is updated to next day
		    String selecteddateprintout2 = tvlist.DatePrintout().getText();
		    
		    String calculateddateprintout2 = GetDatePrintout.newdateprintout(1, timezone);
		    
		    try
		    {
		    	Assert.assertEquals(selecteddateprintout2, calculateddateprintout2, "Scroll forward 2 into next day date printout is incorrect."); //Assert.assertEquals(actual,expected)
		    }
		    catch (AssertionError e)
		    {
				error = e.toString();
				System.out.println(error);
				errorname = "scrollforward2intonextdaydateprintout";
				ScreenshotURL.screenshotURL(driver, foldername, errorname, error);
				softAssert.fail();
		    }
            

	    }
	    else
	    {
		    weekday = CurrentDateTime.easternweekday();
		    shortdate = weekday + CurrentDateTime.easternday();
		    
		    try
		    {
		    	Assert.assertEquals(selectedshortdate, shortdate, "2nd Scroll forward selected date is incorrect."); //Assert.assertEquals(actual, expected)
		    }
			catch(AssertionError e)
			{ 
				error = e.toString();
				System.out.println(error);
				errorname = "scrollforward2selecteddate";
				ScreenshotURL.screenshotURL(driver, foldername, errorname, error);
				softAssert.fail();
			} 
		    
		    
		    //Assert the date printout is today's date
			try
			{
				//Check date printout is current date
				String listingscurrentdate2 = tvlist.DatePrintout().getText();
				System.out.println("TV listings date: " + listingscurrentdate2);
				String currentdate2 = GetDatePrintout.currentdate(timezone);

				Assert.assertTrue(currentdate2.equalsIgnoreCase(listingscurrentdate2), "Scroll Forward 2 Date printout do not match current date.");
			}
			catch (AssertionError e)
			{
				error = e.toString();
				System.out.println(error);
				errorname = "scrollforward2dateprintout";
				ScreenshotURL.screenshotURL(driver, foldername, errorname, error);
				softAssert.fail();
			}
            
		    
	    }	


		softAssert.assertAll();
	}

	
	@AfterMethod 
	public static void OnFailure(ITestResult testResult) throws IOException { 
		if (testResult.getStatus() == ITestResult.FAILURE) 
		{ 
			System.out.println(testResult.getStatus()); 
		} 
	} 
	
	@AfterClass
	public static void Closebrowser(){ 
	
		//Closes the current webdriver browser window
		driver.close();	
		//Closes all the webdriver browser instances
		driver.quit();
	} 
}