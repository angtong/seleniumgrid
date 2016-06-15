package Setup;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;


public class TestBase{
		
		public static WebDriver driver;
		public String URL;
		
		public static String folderpath = "C:\\screencaptures\\";
		
		@Parameters("browser")
		@BeforeMethod
		public void Setup (String browser) throws IOException, MalformedURLException
		{

			DesiredCapabilities cap = null;
			URL = "http://tv.twcc.com";
			
			if(browser.equals("firefox"))
			{

				cap = DesiredCapabilities.firefox();

				cap.setBrowserName("firefox");

				cap.setPlatform(Platform.MAC);

		   }else if (browser.equals("chrome"))
		   {
				cap = DesiredCapabilities.chrome();

				cap.setBrowserName("chrome");

				cap.setPlatform(Platform.WINDOWS);

		   }
		   else if (browser.equals("safari"))
		   {
				cap = DesiredCapabilities.safari();

				cap.setBrowserName("safari");

				cap.setPlatform(Platform.MAC);

		   }else if (browser.equals("internet explorer"))
		   {

				cap = DesiredCapabilities.internetExplorer();

				cap.setBrowserName("internet explorer");

				cap.setPlatform(Platform.WINDOWS);

			}

			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),cap);
			driver.get(URL);
			driver.manage().window().maximize(); 

			//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);			
			
		}		
		
	
	
		@AfterMethod
		public void closeBrowser()
		{
			try
			{
				driver.wait(5000);
			}
			catch (Exception e)
			{
				//Closes the current webdriver browser window
				driver.close();	
				//Closes all the webdriver browser instances
				driver.quit();
			}
		}

		
}		