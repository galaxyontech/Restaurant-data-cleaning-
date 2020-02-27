package project3;
import java.lang.IllegalArgumentException;
/**
 * This creates inspection class. It implements comparable interface
 * and use Date class objects are one of it parameters. 
 * It will be added at the end of restaurant objects to show inspections
 * @author zeruiji
 *
 */

public class Inspection implements Comparable<Inspection> {
	private Date date;
	private int score;
	private String violation;
	private String risk;
	/**
	 * This is the constructor for creating Inspection instances. 
	 * It throws IllegalArgumentException for invalid inputs. 
	 * @param date
	 * @param score
	 * @param violation
	 * @param risk
	 * @throws IllegalArgumentException
	 */
	
	public Inspection( Date date , int score, String violation, String risk) throws IllegalArgumentException {
		// checking the date
		if (date == null) {
			throw new IllegalArgumentException("Error: this is not a valid number for date");
		}
		// checking the score
		if (score < 0 || score > 100) {
			throw new IllegalArgumentException("Error: this is not a valid number for score");
		}
		this.date = date;
		this.score = score;
		this.violation = violation;
		this.risk = risk;
	}
	/**
	 * This method returns the String representation of the instance
	 */
	public String toString() {
		return String.format("%s,%s,%s,%s", date.toString(),Integer.toString(score), violation, risk);
	}
	/**
	 * This method is required due to the implementation of the comparable interface
	 */
	@Override
	public int compareTo(Inspection o) {
		return this.date.compareTo(o.date);
		
	}
	// Getter method for date
	public Date date() {
		return this.date;
	}
	// Getter method for score
	public int score() {
		return this.score;
	}
	// Getter method for violation 
	public String violation() {
		return this.violation;
	}
	// Getter method for risk
	public String risk() {
		return this.risk;
	}

}
