package otherClasses;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class ScreenshotURL {

	@Test
	public static void screenshotURL (WebDriver driver, String foldername, String errorname, String error) throws IOException 
	{
		String url = "";
	  
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE); 
		FileUtils.copyFile(scrFile, new File(""+foldername+"\\"+errorname+".jpeg")); //Windows
		//FileUtils.copyFile(scrFile, new File(""+foldername+"//"+errorname+".jpeg")); //Mac
	 
	    //FilePath
		String filePath = ""+foldername+"\\"+errorname+"_URLResults.txt"; //Windows
		//String filePath = ""+foldername+"//"+errorname+"_URLResults.txt"; //Mac
		url = driver.getCurrentUrl();
		
			try {
			      //Creating File object
			     File file = new File(filePath);
			     // if file doesn't exists, then create it
			     if (!file.exists()) {
			      file.createNewFile();
			     }
			     //Creating FileWriter object
			     //using file object we got the filePath
			     //FileWriter fw = new FileWriter(file.getAbsoluteFile());
			     Boolean append = true;
			     FileWriter fw = new FileWriter(filePath, append);
			     //Creating BufferedWriter object
			     BufferedWriter bw = new BufferedWriter(fw);
			     //Writing content into file
			     bw.write(url);
			     //Adding new line 
			     bw.newLine();
			     bw.write(error);
			     //Adding new line 
			     bw.newLine();
			     bw.close();
	
		    } catch (IOException e2) {
		    	e2.printStackTrace();
		    }
	}
}
