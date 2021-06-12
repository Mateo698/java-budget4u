package model;

import java.io.Serializable;
import java.util.Calendar;

/** Represents an outlay.
* @author https://github.com/Mateo698
* @author https://github.com/KennetSanchez
* @version 1.0
*/
public class Outlay implements Comparable<Outlay>,Serializable{
	
	private static final long serialVersionUID = 1;
	private String name;
	private long amount;
	private Calendar creationDate;
	private String type;
	
	/*
	 * Constructor Outlay.
	 * This method creates a new Outlay. 
	 * @param n 	contains the name of the new outlay. String, not empty neither null.
	 * @param a		contains the amount of the new outlay. Long, cann't be null.
	 * @param cD	contains the calendar of the creation date. Calendar, cann't be null.
	 * @return Outlay, returns a new Outlay.
	 */
	public Outlay(String n, long a, Calendar cD) {
		setName(n);
		setAmount(-a);
		setCreationDate(cD);
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public Calendar getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Calendar creationDate) {
		this.creationDate = creationDate;
	}
	
	public String getDate() {
		String realDate = "";
		realDate = creationDate.get(Calendar.DAY_OF_MONTH) + "/" + creationDate.get(Calendar.MONTH) + "/" + creationDate.get(Calendar.YEAR);
		return realDate;
	}

	/*
	 * Compare to Outlay.
	 * This method compares the creation date with another outlay and returns the higher. 
	 * @param o 	contains another Outlay to compare. Outlay, not empty neither null.
	 * @return bigger, an integer showing which one has the higher creation date.
	 */
	@Override
	public int compareTo(Outlay o) {
		int bigger = creationDate.compareTo(o.getCreationDate());
		return bigger;
	}
	
	public String getType() {
		return type;
	}
	
}
