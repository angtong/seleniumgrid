package otherClasses;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import org.testng.annotations.Test;

public class GetDatePrintout {

		@Test
		public static String currentdate (String timezone) throws InterruptedException {

			Date currentdate = new Date();
		    Calendar c = Calendar.getInstance();
		    c.setTime(currentdate);
		    
		    DateFormat df = new SimpleDateFormat("EEEEE, MMMMM d, y"); //day of week=E day=d //Saturday, March 5, 2016 
		    df.setTimeZone(TimeZone.getTimeZone(timezone));
		    
	        String currentdateprintout = df.format(c.getTime());
			
			System.out.println("currentdateprintout from otherClasses.GetDatePrintout Method: "+currentdateprintout);
			
			return currentdateprintout;
			
		}
		
		@Test
		public static String newdateprintout (int adddays, String timezone) throws InterruptedException {
			Date currentdate = new Date();
		    Calendar c = Calendar.getInstance();
		    c.setTime(currentdate);
		    c.add(Calendar.DATE, adddays);
		    
		    DateFormat df = new SimpleDateFormat("EEEEE, MMMMM d, y"); //day of week=E month=M day=d y=year //Saturday, March 5, 2016 
		    df.setTimeZone(TimeZone.getTimeZone(timezone));
		    
	        String calculateddateprintout = df.format(c.getTime());
			
			System.out.println("New date printout from CurrentDateTime class method: "+calculateddateprintout);
			
			return calculateddateprintout;
					
			
		}
		
		@Test
		public static String newshortdate (int adddays, String timezone) throws InterruptedException {
			Date currentdate = new Date();
		    Calendar c = Calendar.getInstance();
		    c.setTime(currentdate);
		    c.add(Calendar.DATE, adddays);
		    
			DateFormat df = new SimpleDateFormat("Ed"); //day of week=E day=d //THU10
			df.setTimeZone(TimeZone.getTimeZone(timezone));
			
			String shortdate = df.format(c.getTime()).toUpperCase();
			
			System.out.println("New short date from CurrentDateTime class method: "+shortdate);
			
			return shortdate;
					
			
		}
		
		
		
}


