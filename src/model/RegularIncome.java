package model;

import java.io.Serializable;
import java.util.Calendar;

/** Represents a regular income, they are going to be added when the pay date arrives.
 * @author https://github.com/Mateo698
 * @author https://github.com/KennetSanchez 
 * @version 1.0
*/
public class RegularIncome extends Income implements Serializable{
	
	private static final long serialVersionUID = 1;
	private String type = "Regular";
	private Calendar monthlyDate;
	
	/*
	 ** Constructor RegularIncome.
	 * This method creates a new regular income. 
	 * @param n 	contains the name of the new income. String, not empty neither null.
	 * @param a		contains the amount of the income. Long, cann't be null.
	 * @param cDcontains the creation date. 
	 * @param mD contains the date when the income should be added. Calendar, cann't be null.
	 * @return RegularIncome, returns a new regular income.
	 */
	public RegularIncome(String n, long a, Calendar cD, Calendar mD) {
		super(n,a,cD);
		setMonthlyDate(mD);
	}

	public Calendar getMonthlyDate() {
		return monthlyDate;
	}

	public void setMonthlyDate(Calendar monthlyDate) {
		this.monthlyDate = monthlyDate;
	}

	public String getType() {
		return type;
	}
	
	
}
