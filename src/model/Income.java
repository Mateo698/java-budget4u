package model;

import java.io.Serializable;
import java.util.Calendar;

/** Represents an income.
* @author https://github.com/Mateo698
* @author https://github.com/KennetSanchez
* @version 1.0
*/
public class Income implements Comparable<Income>,Serializable{
	
	private static final long serialVersionUID = 1;
	private String name;
	private long amount;
	private Calendar creationDate;
	
	/** Constructor Income.
	 * This method creates a new income. 
	 * @param n 	contains the name of the new income. String, cann't be empty neither null.
	 * @param a		contains the amount of the of the income. Long, cann't be null.
	 * @param cD	contains the creation date of the income. Calendar, cann't be null, neither a future date.
	 * @return Income, returns a new income.
	 */
	public Income(String n, long a, Calendar cD) {
		setName(n);
		setAmount(a);
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
	 * Compare to Income.
	 * This method compares the creation date with another income and returns the higher. 
	 * @param i 	contains another income to compare. Outlay, not empty neither null.
	 * @return bigger, an integer showing which one has the higher creation date.
	 */
	@Override
	public int compareTo(Income i) {
		int bigger = creationDate.compareTo(i.getCreationDate());
		return bigger;
	}
	
}
