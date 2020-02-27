package project3;
import java.text.SimpleDateFormat;
import java.text.ParseException;
/**
 * The Date class is used to represent dates. It should store the numerical values for month, day and year. 
 * The class provide two constructors:
 * It implements comparable interface 
 * @author zeruiji
 * @version 10/14/2019
 *
 */
public class Date implements Comparable<Date>{
	/**
	 * this constructor will accept string form of date inputs and to valid data
	 * it throws IllegalArgumentException
	 */
	private String date;
	public Date(String date) throws IllegalArgumentException  {
		if (date == null) {
			throw new IllegalArgumentException("Error: this not a valid date"); 
			// checks whether the date is null 
		}
		if (date.length() != 10 & date.length() != 8) {
			throw new IllegalArgumentException("Error: this is not a valid date");
			//Checks whether the data is within the bound
		}	
		if (date.length() == 10) {
			if (!valid_date(date)) {
				throw new IllegalArgumentException("Error:this is not a valid date"); // valid the format of date
			}else {
				int temp_month = Integer.parseInt(date.substring(0,2));
				int temp_year = Integer.parseInt(date.substring(6));
				int temp_day = Integer.parseInt(date.substring(3,5)); // parsing the date into separate month, day, year variables
				setDate(temp_month,temp_day,temp_year);
			}
		}else if(date.length() == 8) {
			// same operation with the previous one except the length is 8
			int month = Integer.parseInt(date.substring(0,2));
			int year = Integer.parseInt(date.substring(6))+2000;
			int day = Integer.parseInt(date.substring(3,5));

			
			String new_date = date.substring(0,6)+"20"+date.substring((6));
				if(valid_date(new_date)) {
					setDate(month,day,year);
				}

	}
}
	/**
	 * this constructor accepts Integer forms of month,day and year. 
	 * It throws IllegalArgumentException.
	 * @param month
	 * @param day
	 * @param year
	 * @throws IllegalArgumentException
	 */
	public Date(int month, int day, int year) throws IllegalArgumentException {
		setDate(month,day,year);
		
	}
	/**
	 * This boolean checks whether the date entered is within the regular form of dates 
	 * It should consider all cases
	 * @param date
	 * @return boolean
	 * 
	 */
	public boolean valid_date(String date) {
			int month = Integer.parseInt(date.substring(0,2));
			int year = Integer.parseInt(date.substring(6));;		
			int day = Integer.parseInt(date.substring(3,5));

		if(month < 1 || month > 12) { 
			// checks the month
			return false;
		}
		if ( year < 2000 || year > 2025) {
			// checks the year 
			return false;
			
		}
		// checks the month by using the switch statement due to various month
		switch (month) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			if ( day < 1 || day > 31) {
				return false;
			}
			break;
		case 2:
			// checks the special case of February
			
			
			if (year == 2000 | year == 2004 || year == 2008 || year == 2012 || year == 2016 || year == 2020 || year == 2024) {
				if (day <0 || day>29) {
					return false;
				}
			}
			if (day < 1 || day > 28) {
				 return false;
			}
			break;
		
		case 4:
		case 6:
		case 9:
		case 11:
			if ( day < 1 || day > 30) {
				return false;
			}
			break;	
		
		}
		return true;
		
	}
	/**
	 * This method valid the data input 
	 * This method throws IllegalArgumentException for different cases. 
	 * @param month
	 * @param day
	 * @param year
	 */
	public void setDate(int month, int day, int year) {
		if(month < 1 || month > 12) {
			// checks the month, throws an exception
			throw new IllegalArgumentException("Error : Incorrect value for month");
		}
		if ( year < 2000 || year > 2025) {
			// checks the year, throws an exception
			throw new IllegalArgumentException("Error : Incorrect value for year");
		}
		switch (month) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			if ( day < 1 || day > 31) {
				throw new IllegalArgumentException("Error : Incorrect value for day");
			}
			break;
		case 2:
			if (year == 2000 || year == 2004 || year == 2008 || year == 2012 || year == 2016 || year == 2020 || year == 2024) {
				if (day <1 || day>29) {
					throw new IllegalArgumentException("Error : Incorect value for day");
				}
			}	
			else if (day < 1 || day > 28) {
				 throw new IllegalArgumentException("Error: Incorrect value for day");
			}
			break;
		
		case 4:
		case 6:
		case 9:
		case 11:
			if ( day < 1 || day > 30) {
				throw new IllegalArgumentException("Error: Incorrect value for day");
			}
			break;	
		} 
		String str_Month = "";
		String str_Day = "";
		String str_Year = "";
		
		//parsing year, month and day into separate strings and then
		// combine these strings together. 
		
		if (month < 10) {
			str_Month = "0" + Integer.toString(month);
		}else {
			str_Month = Integer.toString(month);
		}
		if (day <10) {
			str_Day = "0" + Integer.toString(day);
		}
		else {
			str_Day = Integer.toString(day);
			}
		str_Year = Integer.toString(year);
		String str_Date = str_Month +"/"+ str_Day +"/"+ str_Year;
		// set the date as string
		this.date = str_Date;
		
	}
	/**
	 * This method is required due to the implementation of comparable interface.
	 * It compares two dates based on specification required in the project 2, through month, day, and year. 
	 * @return int
	 */
	@Override
	public int compareTo(Date o) {
		int diff_year =Integer.parseInt(this.date.substring(6))-Integer.parseInt(o.date.substring(6));
		int diff_month = Integer.parseInt(this.date.substring(0,2))-Integer.parseInt(o.date.substring(0,2));
		int diff_day = Integer.parseInt(this.date.substring(3,5))- Integer.parseInt(o.date.substring(3,5));
		if(diff_year > 0)
			return 1;
		else if (diff_year < 0)
			return -1;
		else if (diff_year == 0) {
			if (diff_month > 0)
				return 1;
			else if(diff_month <0)
				return -1;
			else if(diff_month == 0) {
				if (diff_day > 0)
					return 1;
				else if (diff_day < 0){
					return -1;
				}
				
			}
		}
		return 0;
	}
	/**
	 * This method returns a String representation of the date. 
	 * @ String
	 */
	@Override
	public String toString() {
		return this.date;
		
	}

	// this is a static method that test the input of Date. It returns boolean value.
	public static boolean isValid1(String value) {
	        try {
	            new SimpleDateFormat("mm/dd/yyyy").parse(value); // using java.util.SimpleDateFormat import to parse the value
	            return true;
	        } catch (ParseException e) { 
	        	// this catches the exception if the value non parsable.
	        	
	            return false;
	        }
	    }
	public static boolean isValid2 (String Value) {
		try {
			new SimpleDateFormat("mm/dd/yyyy").parse(Value);
			return true;
			
		}catch(ParseException e) {
			return false;
		}
	}
}
