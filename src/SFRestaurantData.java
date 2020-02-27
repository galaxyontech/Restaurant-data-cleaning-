package project3;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import java.io.FileNotFoundException;
 /** this class is the program performing date conversion. The program has a input of different formats of Restaurant data. 
  * The program will convert them into readable formats and support users to do searches base on keyword.  
  * @author Zerui Ji
  * In the Interactive part, the users can search for names and zipcodes. and then the console will shows the matching entry. 
  */

public class SFRestaurantData {
	public static void main (String[] args) {
		//verify that the command line argument exists
		if(args.length == 0) {
			System.err.println("UsageError: the program expects file name as an argument.\n");
			System.exit(1);
		}
		//verify that command line argument contains a name of an existing file 
		File dataInput  = new File(args[0]);
		if(! dataInput.exists()) {
			System.out.println("Error: the file restaurant_scores.csv " + dataInput.getAbsolutePath() + "does not exist. \n");
			System.exit(1);
		}
		if (!dataInput.canRead()) {
			System.out.println("Error: the file" + dataInput.getAbsolutePath() + "cannot be open for reading");
			System.exit(1);
		}
		// open the file for reading

		Scanner inData = null;
		try {
			inData = new Scanner(dataInput);
		}
		catch(FileNotFoundException e){
			System.out.println("Error: the file" + dataInput.getAbsolutePath() + "cannot be open for reading");		
		}
		// these following variables are declared to store the variables dynamically within the loop. 
		RestaurantList list = new RestaurantList();
		list.clear();
		Restaurant restaurant = null;
		Date date = null;
		String line = null;
		ArrayList<String> data_list = new ArrayList<String>();
		String cur_date = null;
		int score = 0;
		String violation = null;
		String risk = null;
		String phone = null;
		String name = null;
		String address = null;
		String zip = null;
		// Start reading the file
		while(inData.hasNextLine()){
			try {
				line = inData.nextLine();
				//System.out.println(line);
				data_list = splitCSVLine(line);
				String[] temp = data_list.get(11).split(" ");
				cur_date = temp[0];
				score = Integer.parseInt(data_list.get(12));
				violation = data_list.get(15);
				name = data_list.get(1);
				risk = data_list.get(data_list.size()-1);
				phone = data_list.get(9);
				address = data_list.get(2);
				zip  = data_list.get(5);
				
				// store the data into different variables for the future usages
				
			}	catch (Exception ex){
				// Caused by miss formatted line in the input file. 
						// the program continues for the next data input
						continue;
				}
				date = new Date(cur_date);
				try {
					Inspection inspection = new Inspection(date, score, violation, risk);	// creates the inspection class
					restaurant = new Restaurant(name,zip,address, phone);					// creates the restaurant class
				for (Restaurant c:list) {
					if (c.equals(restaurant) && c.getInspection().size() < 2) {				// adds the additional Inspections. 
						c.addInspection(inspection);
						break;
					}
				}if (!list.contains(restaurant)) {
					restaurant.addInspection(inspection);
					list.add(restaurant);
				}
				}catch(IllegalArgumentException ex){
					//ignore this exception and skip to the next line
				}
			}
		// this is the user Interactive mode. The user is given with choices to search for name, zip or quit the interaction. 

		System.out.println("\nSearch the database by matching keywords to titles or actor names.\n"
				+ "  To search for matching restaurant names, enter " +"\n    name KEYWORD" 
				+ "\n  To search for restaurants in a zip code, enter\n    zip KEYWORD"
				+"\n  To Finish the program, enter\n    quit");
		Scanner userInput;
		String userValue;
		String[] userValue_list;
		do {
			// starts reading the user input 
			
			System.out.println("Enter your search query: \n\n");
			userInput = new Scanner(System.in);
			userValue = userInput.nextLine();
			if (userValue.equalsIgnoreCase("quit")) {
				System.exit(0);									// the program will exit if the user enter quit for the interactive mode
			}
			userValue_list = userValue.split(" ");
			// split the userinput and checks for why query should be operated with
			while ( userValue_list.length != 2 && (!userValue_list[0].trim().equalsIgnoreCase("name") && !userValue_list[0].trim().equalsIgnoreCase("zip"))){
				System.out.println("This is not a Valid query. Please try again");
					userValue = userInput.nextLine();
					userValue_list = userValue.split(" ");		
			}
			String keyword = userValue_list[1];
			// if the user types in name 
			if (userValue_list[0].equalsIgnoreCase("name")) {
				RestaurantList outputList = new RestaurantList();
				outputList = list.getMatchingRestaurants(keyword);
				// the program should continue to prompt user to enter queries due to no result has been found.
				if (outputList == null){
					System.out.println("No matches found, Try Again. \n\n");
					continue;
				}
				outputList.sortbyS(outputList);
				if (outputList.size() >3 ) {
					for (int i = 0; i< 3; i++) {
						System.out.println(outputList.get(i).toString());
					}
					}else {
						for (Restaurant c: outputList) {
							System.out.println(c.toString());
						}
					}	
			}else if(userValue_list[0].equalsIgnoreCase("zip")) {
				// if the user type in zip 
				RestaurantList outputList = new RestaurantList();
				outputList = list.getMatchingZip(keyword);
				if (outputList == null) {
					System.out.println("No matches found, Try Again. \n\n");
					continue;
				}else {
					outputList.sortbyS(outputList);
					if (outputList.size() >3 ) {
						for (int i = 0; i< 3; i++) {
							System.out.println(outputList.get(i).toString());
						}
						}else {
							for (Restaurant c: outputList) {
								System.out.println(c.toString());
							}
						}
				}
			}
		}while(!userValue.equalsIgnoreCase("quit"));
		userInput.close();
}

	
	/**
	* Splits the given line of a CSV file according to commas and double quotes
	* (double quotes are used to surround multi-word entries so that they may contain commas)
	* @param textLine  a line of text to be passed
	* @return an Arraylist object containing all individual entries found on that line
	*/
	public static ArrayList<String> splitCSVLine(String textLine){
	if (textLine == null ) return null;
		ArrayList<String> entries = new ArrayList<String>(); int lineLength = textLine.length();
		StringBuffer nextWord = new StringBuffer(); char nextChar;
		boolean insideQuotes = false; boolean insideEntry= false;
		// iterate over all characters in the textLine
		for (int i = 0; i < lineLength; i++) { 
			nextChar = textLine.charAt(i);
			// handle smart quotes as well as regular quotes
			if (nextChar == '"' || nextChar == '\u201C' || nextChar =='\u201D') {
					// change insideQuotes flag when nextChar is a quote 
				if (insideQuotes) {
					insideQuotes = false; 
					insideEntry = false;
				}else {
					insideQuotes = true;
					insideEntry = true; 
					}
	} else if (Character.isWhitespace(nextChar)) { 
		if ( insideQuotes || insideEntry ) {
			// add it to the current entry
			  nextWord.append( nextChar );
	}else { // skip all spaces between entries 
		continue;
		}
	} else if ( nextChar == ',') {
	if (insideQuotes){ // comma inside an entry 
		nextWord.append(nextChar);
	} else { // end of entry found
		insideEntry = false; 
		entries.add(nextWord.toString());
		nextWord = new StringBuffer(); }
	} else {
	// add all other characters to the nextWord
	nextWord.append(nextChar); 
	insideEntry = true;
		}
	}
	// add the last word ( assuming not empty )
	// trim the white space before adding to the list 
		if (!nextWord.toString().equals("")) {
			entries.add(nextWord.toString().trim());
		}
	return entries;
	}
}

