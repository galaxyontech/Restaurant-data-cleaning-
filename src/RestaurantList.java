package project3;

import java.util.Arrays;

/**
 * this class store list of restaurant objects 
 * It provides two method of searching by name or zipcode.
 * It returns the list with sorted natural orders; 
 * @author zeruiji
 * @version 10/14/2019
 *
 */

public class RestaurantList extends LinkedList<Restaurant> {

	public RestaurantList() {
		// this is a default constructor
	}
	/**
	 * Return a list of Restaurant objects whose names contain the keyword as a substring (case insensitive).
	 * The returned lists should be sorted according to the natural order4 of their elements. 
	 * @param Keyword
	 * @return RestaurantList
	 */
	public RestaurantList getMatchingRestaurants(String Keyword) {
		 if (Keyword == null || Keyword.length() == 0) 
			 return null;					// check if the keyword is valid 
		 RestaurantList list = new RestaurantList(); // create an empty instance of RestaurantList
		 for (Restaurant c: this) {
			 String Restaurant_name = c.getName(); // using a for each loop to check each element 
			 if (Restaurant_name.toLowerCase().contains(Keyword.toLowerCase())) {
				 list.add(c);				// if keyword is contained in the name, the list would add the restaurant
			 }
		 }	
		 if (list.size() == 0)  // checks the size of list
			 return null;
		list.sort(); // sort the list
		return list;
		 
	}
	/**
	 * Return a list of Restaurant object whose zip codes are equal to the keyword. 
	 * The returned lists should be sorted according to the natural order4 of their elements. 
	 * @param Keyword
	 * @return
	 */
	public RestaurantList getMatchingZip(String Keyword) {
		RestaurantList list = new RestaurantList();
		for (Restaurant c: this) {							// using the foreach loop to go through the entire list checking the for zip code
			String Restaurant_zip = c.getZip();
			if (Keyword == null || Keyword.length()== 0) 
				return null;
			if (Restaurant_zip.toLowerCase().equals(Keyword)) {     // the zipcode is checked
				list.add(c);
			}
		}
	
	list.sort();
	if(list.size() == 0) {   // checks for the actual size
		return null;
	}else {
		return list;
	}
}
	/**
	 * This method represents a String representation of Restaurantlist object. 
	 * @return String
	 */
	public String toString() {
		String output = "";
		for (Restaurant c: this) {
			output += c.getName() + "; ";
		}
		return output.substring(0,output.length()-2);
	}
	/**
	 * This method is to sort the outputlist by the descending order of scores. 
	 * The method first convert the entire linkedlist to array and then sort it by using the SortbyScore class defined in Restaurant class
	 * Then the method converts it back to a linkedlist
	 * @param list
	 * @return
	 */
	public RestaurantList sortbyS(RestaurantList list) {
		Restaurant[] output = new Restaurant[list.size()];
		for (int i =0 ; i< list.size(); i++) {		// copying every element
			output[i] = list.get(i);
		}
		Arrays.sort(output, new SortbyScore());
		list.clear();
		for(Restaurant o: output) {			// adding to the linkedlist
			this.add(o);
		}
		return this;
	}
}
