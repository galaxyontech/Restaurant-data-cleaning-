package project3;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
/**
 * This class represents Restaurant
 * It uses two constructors to create Restaurant objects. The first constructor accepts the two parameters 
 * The second accepts 4 parameters. Each constructor should be able to handle all illegal argument. The constructor should return an error message
 * Acknowledging users that they have entered illegal arguments. 
 * @author zeruiji
 * @version 10/16/2019
 */

public class Restaurant implements Comparable<Restaurant>{
	public static void main(String[] args) {
		Restaurant res1 = new Restaurant("hello", "123");
		Restaurant res2 = new Restaurant("hello", null);
		System.out.println(res1.equals(res2));
	}
	private String name;
	private String address;
	private String phone;
	private String zip;
	private ArrayList<Inspection> list_Inspection = new ArrayList<Inspection>();
	
	/** construct a new Restaurant object with specific name and zip code, the validity of datainput will be checked in the next constructor
	 * @throws IllegalArgumentException if the parameter of both constructors are invalid.
	 */
	public Restaurant(String name, String zip) throws IllegalArgumentException{
		this(name, zip, null, null);
	}
	/**
	 * This is the second constructor for Restaurant class. It takes four parameters
	 * The constructor handles IllegalArgumentException for any exceptions. 
	 * @param name is the name of Restaurant
	 * @param zip provides the zipcode
	 * @param address 
	 * @param phone
	 * @throws IllegalArgumentException if the parameters are invalid 
	 */
	public Restaurant(String name, String zip, String address, String phone) throws IllegalArgumentException {
		if(name.length() == 0 ) {			// checks the validity for name 
			throw new IllegalArgumentException("Invalid name for this Restaurant");
		}
		if (zip.length() != 5) {			// checks the validity for length
			throw new IllegalArgumentException("Invalid Zipcode for this Restaurant");
		}
		for (int i = 0; i< zip.length() ; i++) {     
											// checks the validity for zip
			if (!Character.isDigit(zip.charAt(i)))
				throw new IllegalArgumentException("Error: Invalid Zipcode for this Restaurant");
		}
		this.name = name;
		this.zip = zip;
		this.address = address;
		this.phone = phone;
		
	}
	@Override
	/**
	 * this method overrides the compareTo method by implementing comparable interface
	 * @return int
	 *
	 */
	public int compareTo (Restaurant o) {
		String o_newName = o.name.toLowerCase();  // transform all parameter to lower case
		String newName = name.toLowerCase();
		if (o == null) 					// checks for the null scenarios
			return -2;
		int result = newName.compareTo(o_newName);  // compare for the results
		if (result == 0)
			result = zip.compareTo(o.zip);
		return result;	
	}
	@Override
	/**
	 * this method overrides the equals method by implementing comparable interface
	 * @return boolean
	 */
	public boolean equals(Object obj) {
		if (this == obj)				// checks for the reference
			return true;
		if (obj == null)				// checks for the null scenarios
			return false;
		if (!(obj instanceof Restaurant))  // checks whether two objects belong to the same class
			return false;
		Restaurant other = (Restaurant) obj;
		if (!name.equalsIgnoreCase(other.name)) {			// now, start to check for the parameters. 
			return false;
		}
		if (zip == null) {
			if (other.zip != null) 
				return false;
		}else if (zip.compareTo( other.zip) != 0) 		// checks the the last one
				return false;
		
		return true;   			// we have checked all the scenarios, we can know return the true statement
	}
	/**
	 * This method add Inspection to the inspection list. Because there potential exist different inspection dates---
	 * for the same restaurant. The method needs to add the additional inspection to the existing Restaurant.
	 * @param Inspect
	 */
	public void addInspection(Inspection Inspect) {
		if (Inspect == null) {
			throw new IllegalArgumentException("Error: the inspection is empty");
		}
		list_Inspection.add(Inspect);
	}
	/**
	 * This method provides a String representation of the Restaurant objects
	 * @return String
	 */
	@Override
	public String toString() {
		// sort the inspection first. 
		Collections.sort(list_Inspection);  // sort the inspection list to give the most recent inspection
		String result = "";
		for (int i = 0 ; i< list_Inspection.size() ; i++) {
			result+="     "+ list_Inspection.get(i).score()+", "+ list_Inspection.get(i).date()+", "+ list_Inspection.get(i).risk() +", " + list_Inspection.get(i).violation() + "\n";
		}
		String output = " ";
		output = name + "\n" + "------------------------------------\n" + "address:          " + this.address + "\n" + "zip:              " + this.zip + "\n" + "phone:            " + this.phone + "\nrecent inspection results: \n"
				+ result;
		
		return output;
				
	}
	/**
	 * these methods return the necessary parameters to create the restaurant class 
	 * provides convenience when using them in other classes
	 * @return
	 */
	
	public String getName() {
		return this.name;
	}
	public String getZip() {
		return this.zip;
	}
	public String getPhone() {
		return this.phone;
	}
	public String getAddress() {
		return this.address;
	}
	public ArrayList<Inspection> getInspection() {
		return list_Inspection;
	}
}	
/**
 * This is a separate class used to compare the Restaurant with score by implementing comparator interface. 
 * The Restaurant will be compared base on the descending order of integers 
 * @author zeruiji
 * @version 10/16/2019
 */
	class SortbyScore implements Comparator<Restaurant>{

		@Override
		public int compare(Restaurant o1, Restaurant o2) {
			int score1 = o1.getInspection().get(0).score();
			int score2 = o2.getInspection().get(0).score();
			return score2 - score1;
		}
		
	}
	

	

