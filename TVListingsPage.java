package PageObjects;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import Setup.TestBase;
import otherClasses.CurrentDateTime;

public class TVListingsPage extends TestBase {

	final static int waitseconds = 60;
    WebDriverWait wait = new WebDriverWait(driver, waitseconds);
    String currenttime = "";

     
    By watchtv = By.cssSelector("div#watchtv-widget>h2>a");
    By fulltvlistings = By.cssSelector("div#watchtv>div:nth-of-type(1)>div:nth-of-type(2)>a");
    //By zipcode = By.xpath("//div[@class='region']/a"); 
    //can't use because tvlistings_genres_ca_test can't find the xpath
    By zipcode = By.cssSelector("div.listings-header-container>div.listings-header>h1>div.region>a");
    By zipcodefield = By.id("zip");
    By tvprovider = By.id("headend");
    By tvprovideroptions = By.tagName("option");
    By savezip = By.id("region-submit");
    By regionregion = By.xpath("//div[@class='region']/a");
    By assertregion = By.xpath("//div[contains(text(), 'Local listings for:') and @class='region']");
    By assertpacificregion = By.xpath("//div[@class='region']/a[@href='#' and text()='Time Warner Cable City of Los Angeles... [90210]']");
    
	By click_forward1 = By.xpath("//a[@title='later']");
    By click_backward1 = By.xpath("//a[@title='earlier']");  
    By Ahr = By.cssSelector("li.hours>div:nth-of-type(1)>p");
    By Bhr = By.cssSelector("li.hours>div:nth-of-type(2)>p");
    By Chr = By.cssSelector("li.hours>div:nth-of-type(3)>p"); 
    By tvlistingsheader = By.xpath("//div[@class='listings-header']/h1");
    By selected_date = By.className("selected"); 
    //couldn't use xpath //li[@class[contains(., 'selected')]]/a IE couldn't find it and got wrong element
    By date_printout = By.cssSelector("#mydate");
    
    By primetime = By.id("goP");
    By nowradiobutton = By.xpath("//input[@id='goN']");
    By schedule = By.xpath("//ul[@id='wgridCalendar']/li");

    By jumptochannel = By.id("jumpToChannelInput"); 
    String firstdateid = GetFirstDateID();
    By firstdate_id = By.id(firstdateid);
    
    public String GetFirstDateID()
    {
    	String firstdateid = "";
    	int pacificday = CurrentDateTime.pacificday();
    	String day = Integer.toString(pacificday);
    	String month = CurrentDateTime.pacificmonth().substring(0, 3);
    	String year = CurrentDateTime.pacificyear();
    	
        String [] singledays = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
        
    	if (Arrays.asList(singledays).contains(day))
		{
			firstdateid = "C0"+day+"-"+month+"-"+year+"";
		}
		else
		{
			firstdateid = "C"+day+"-"+month+"-"+year+"";
		}
		
		System.out.println(firstdateid); //id="C27-Dec-2015"
		
		return firstdateid;
    }
    By threedays_inschedule = By.xpath("//li[@id='"+firstdateid+"']/following-sibling::li[3]");
    By sevendays_inschedule = By.xpath("//li[@id='"+firstdateid+"']/following-sibling::li[7]");
    By elevendays_inschedule = By.xpath("//li[@id='"+firstdateid+"']/following-sibling::li[11]");
    By scroll_back = By.xpath(".//li[@class='navlspacer']/div/a");
    By programs = By.xpath("//li[@id='page-1']/ul/li[2]/ul/li[2]/div[1]/div[1]/a");
    By program = By.xpath("//li[@id='page-1']/ul/li[2]/ul/li[2]/div[1]/div[1]/a");
    By contentsummarypopup = By.cssSelector("div#contentSummary_body>div>div:nth-of-type(1)>a>img");
    By contentsummarypopup_programtitle = By.cssSelector("div#contentSummary_body>div>div:nth-of-type(2)>ol>li:nth-of-type(1)>a");
    By contentsummarypopup_synopsis = By.cssSelector("div#contentSummary_body>div>div:nth-of-type(2)>ol>li:nth-of-type(3)");
    By contentsummarypopup_moreepisodes = By.cssSelector(".btn.btn-primary.see-more-episodes");
    By contentsummarypopup_posterimage = By.className("lazy");
    By contentsummarypopup_backtotvlistings = By.linkText("Back to TV Listings");
    By contentsummarypopup_subtitle = By.cssSelector("div#contentSummary_body>div>div:nth-of-type(2)>ol>li:nth-of-type(2)>a");
    By channel = By.className("channel");
    By nickelodeonchanname = By.xpath("//li[@data-name[contains(., 'Nickelodeon')]]");
    By tvlistingsresults = By.className("gridLoader");
    By moreepisodes = By.cssSelector(".btn.btn-primary.see-more-episodes");
    By seemoreinfo = By.linkText("See More Info");
    By foximage = By.xpath("//img[@alt='Fox']");
    
    By nowicon = By.cssSelector("div#timenow");
    By nowverticalbar = By.cssSelector("div.verticalTimeLine");
    By nonowicon=By.cssSelector("div#timenow[style='display: none;']");
    By nonowverticalbar=By.cssSelector("div.verticalTimeLine[style='display: none;']");
    
    By VH1image = By.xpath("//img[@alt='VH1']");
    By MTVimage = By.xpath("//img[@alt='MTV']");
    By Historyimage = By.xpath("//img[@alt='History']");
    By jumptochannel_channelslistresults = By.xpath("//div[@id='jumpToChannelOutput']/ul/li");
    
    By nosearchresults = By.cssSelector("ul.resultsFail>li>div");
    
    public WebElement WatchTV()
    {
    	wait.until(ExpectedConditions.elementToBeClickable(watchtv));
    	return driver.findElement(watchtv);
    }
     
    public WebElement FullTVListings ()
    {
    	wait.until(ExpectedConditions.elementToBeClickable(fulltvlistings));
    	return driver.findElement(fulltvlistings);
    }

	public WebElement Zipcode ()
	{
		wait.until(ExpectedConditions.elementToBeClickable(zipcode));
	    return driver.findElement(zipcode);
	}
	
	public WebElement ZipcodeField()
	{
		wait.until(ExpectedConditions.elementToBeClickable(zipcodefield));
		return driver.findElement(zipcodefield);
	}
	
	public WebElement TVProvider ()
	{
		wait.until(ExpectedConditions.presenceOfElementLocated(tvprovider));
		return driver.findElement(tvprovider);
	}
	
	public List <WebElement> TVProviderOptions ()
	{
		WebElement dropdownlist = TVProvider();
	    return dropdownlist.findElements(tvprovideroptions);
	}
	
	public WebElement SaveZipcode()
	{
		wait.until(ExpectedConditions.elementToBeClickable(savezip));
		return driver.findElement(savezip);
	}
	
	public WebElement RegionSetting()
	{
		wait.until(ExpectedConditions.presenceOfElementLocated(regionregion));
		return driver.findElement(regionregion);
	}
	
	public String GetCurrentTimeUsingRegion (String region)
	{
	
		switch (region)
		{
			case "Time Warner 13 (New York) [10001]":
				currenttime = CurrentDateTime.easterncurrenttime();
				break;
			case "Time Warner Southern Manhattan (New Y... [10002]":
				currenttime = CurrentDateTime.easterncurrenttime();
				break;
			case "Time Warner Cable (Austin) [78722]":
				currenttime = CurrentDateTime.centralcurrenttime();
				break;
			case "Time Warner Cable (El Paso) [88008]":
				currenttime = CurrentDateTime.mountaincurrenttime();
				break;
			case "Time Warner Cable City of Los Angeles... [90210]":
				currenttime = CurrentDateTime.pacificcurrenttime();
				break;
			case "GCI Cable (Anchorage) [99508]":
				currenttime = CurrentDateTime.alaskacurrenttime();
				break;
			case "Oceanic Time Warner Oahu (Honolulu) [96801]":
				currenttime = CurrentDateTime.hawaiicurrenttime();
				break;
				
		}
		System.out.println("currenttime: "+currenttime);
		return currenttime;

	
	}
	
	public List <WebElement> AssertRegion ()
	{
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(assertregion));
		return driver.findElements(assertregion);
	}
	
	public List <WebElement> AssertPacificRegion ()
	{
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(assertpacificregion));
		return driver.findElements(assertpacificregion);
	}
	

    
    public WebElement ClickForward1()
    {
    	wait.until(ExpectedConditions.presenceOfElementLocated(click_forward1));
        return driver.findElement(click_forward1);
    }
    
    public WebElement ClickBackward1()
    {
    	wait.until(ExpectedConditions.presenceOfElementLocated(click_backward1));
        return driver.findElement(click_backward1);
    }
    
    public WebElement AHr()
    {
    	wait.until(ExpectedConditions.presenceOfElementLocated(Ahr));
    	return driver.findElement(Ahr);
    }

    public WebElement BHr()
    {
    	wait.until(ExpectedConditions.presenceOfElementLocated(Bhr));
    	return driver.findElement(Bhr);
    }
    
    public WebElement CHr()
    {
    	wait.until(ExpectedConditions.presenceOfElementLocated(Chr));
    	return driver.findElement(Chr);
    }    
    
    public WebElement TVListingsHeader()
    {
    	wait.until(ExpectedConditions.presenceOfElementLocated(tvlistingsheader));
    	return driver.findElement(tvlistingsheader);
    }
    
    
    public WebElement SelectedDate()
    {
    	wait.until(ExpectedConditions.presenceOfElementLocated(selected_date));
    	return driver.findElement(selected_date);
    }	
    
    public WebElement DatePrintout()
    {
    	wait.until(ExpectedConditions.presenceOfElementLocated(date_printout));
    	return driver.findElement(date_printout);
    }
	
  
    
    
    public WebElement Primetime()
    {
		wait.until(ExpectedConditions.elementToBeClickable(primetime));
		return driver.findElement(primetime);
    }
    
    public WebElement NowRadioButton()
    {
    	wait.until(ExpectedConditions.elementToBeClickable(nowradiobutton));
    	return driver.findElement(nowradiobutton);
    }
    
    public List <WebElement> Schedule()
    {
    	wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(schedule));
    	return driver.findElements(schedule);
    }
    
    public WebElement JumpToChannel()
    {
    	wait.until(ExpectedConditions.elementToBeClickable(jumptochannel)); 
    	return driver.findElement(jumptochannel);
    }
	
    
    public WebElement GetFirstDateIDElement()
    {
    	wait.until(ExpectedConditions.elementToBeClickable(firstdate_id));
    	return driver.findElement(firstdate_id);
    }
    
    
    public WebElement ThreeDaysInSchedule()
    {
    	wait.until(ExpectedConditions.elementToBeClickable(threedays_inschedule));
    	return driver.findElement(threedays_inschedule);
    }
    
    public WebElement SevenDaysInSchedule()
    {
    	wait.until(ExpectedConditions.elementToBeClickable(sevendays_inschedule));
    	return driver.findElement(sevendays_inschedule);
    }
    
    public WebElement ElevenDaysInSchedule()
    {
    	wait.until(ExpectedConditions.elementToBeClickable(elevendays_inschedule)); 
    	return driver.findElement(elevendays_inschedule);
    }
	
    public WebElement ScrollBack()
    {
    	wait.until(ExpectedConditions.elementToBeClickable(scroll_back));
    	return driver.findElement(scroll_back);
    }
    
    public List <WebElement> Programs()
    {
    	wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(programs));
    	return driver.findElements(programs);
    }
    
    public WebElement Program()
    {
    	wait.until(ExpectedConditions.elementToBeClickable(program));
    	return driver.findElement(program); 
    }
    
    public List <WebElement> ContentSummaryPopup()
    {
    	wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(contentsummarypopup));
	    return driver.findElements(contentsummarypopup);
    }
    
    public List <WebElement> ContentSummaryPopupProgramTitle()
    {
    	wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(contentsummarypopup_programtitle));
    	return driver.findElements(contentsummarypopup_programtitle);
    }
    
    public List <WebElement> ContentSummaryPopupSubtitle()
    {
    	wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(contentsummarypopup_subtitle));
	    return driver.findElements(contentsummarypopup_subtitle);   
    }
    
    public List <WebElement> ContentSummaryPopupSynopsis()
    {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(contentsummarypopup_synopsis));
        return driver.findElements(contentsummarypopup_synopsis);
    }
    
    public List <WebElement> ContentSummaryPopupMoreEpisodes()
    {
    	//don't use wait because if not found it will fail the test
    	return driver.findElements(contentsummarypopup_moreepisodes);
    }
    
    public WebElement MoreEpisodes()
    {
    	//don't use wait because if not found it will fail the test
    	return driver.findElement(moreepisodes);
    }
    
    public WebElement SeeMoreInfo()
    {
    	//don't use wait because if not found it will fail the test
    	return driver.findElement(seemoreinfo); 
    }
    
    public List <WebElement> ContentSummaryPopupPosterImage()
    {
    	wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(contentsummarypopup_posterimage));
	    return driver.findElements(contentsummarypopup_posterimage);
    }
    
    public WebElement ContentSummaryPopupBacktoTVListings()
    {
    	wait.until(ExpectedConditions.elementToBeClickable(contentsummarypopup_backtotvlistings));
    	return driver.findElement(contentsummarypopup_backtotvlistings);
    }
    
    public List <WebElement> Channel()
    {
	    wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(channel));
	    return driver.findElements(channel);
	}
    
    public List <WebElement> NickelodeonChanName()
    {
    	wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(nickelodeonchanname));
    	return driver.findElements(nickelodeonchanname);
    }
    
    public List <WebElement> TVListingsResults()
    {
    	wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(tvlistingsresults));
    	return driver.findElements(tvlistingsresults);
    }
    
    public List <WebElement> FoxImage()
    {
    	wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(foximage));
    	return driver.findElements(foximage);
    }
    
    
    public List <WebElement> NowIcon()
    {
    	wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(nowicon));
    	return driver.findElements(nowicon);
    }
    
    public List <WebElement> NowVerticalBar()
    {
    	wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(nowverticalbar)); 
		return driver.findElements(nowverticalbar);	
    }
    
    public List <WebElement> NoNowIcon()
    {
    	wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(nonowicon));
    	return driver.findElements(nonowicon);
    }
    
    public List <WebElement> NoNowVerticalBar()
    {
    	wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(nonowverticalbar)); 
		return driver.findElements(nonowverticalbar);	
    }
    
    public List <WebElement> VH1Image()
    {
	    wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(VH1image));
	    return driver.findElements(VH1image);
	}
    
    public List <WebElement> MTVImage()
    {
    	wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(MTVimage));
    	return driver.findElements(MTVimage);
    }
    
    public List <WebElement> HistoryImage()
    {
    	wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(Historyimage));
    	return driver.findElements(Historyimage);
    }
    
    
    public List <WebElement> JumptoChannelChannelsListResults ()
    {
    	//Don't use wait, if not found cause scripts to fail and not go to tvlistings page
    	//wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(jumptochannel_channelslistresults));
	    return driver.findElements(jumptochannel_channelslistresults);
    }
    
    
    public WebElement NoSearchResults ()
    {
	    wait.until(ExpectedConditions.elementToBeClickable(nosearchresults));
        return driver.findElement(nosearchresults);
    }

    
}
