package model;

import java.util.Calendar;

/** Represents an ordinary outlay, it's going to be discounted when the pay date arrives.
* @author https://github.com/Mateo698
* @author https://github.com/KennetSanchez
* @version 1.0
*/
public class OrdinaryOutlay extends Outlay{

	private static final long serialVersionUID = 1L;
	private Calendar discountDate;
	private String type;
	
	/*
	 * Constructor OrdinaryOutlay.
	 * This method creates a Ordinary outlay. 
	 * @param n 	contains the name of the new outlay. String, not empty neither null.
	 * @param a		contains the amount of the new outlay. Long, cann't be null.
	 * @param cD	contains the calendar of the creation date. Calendar, cann't be null.
	 * @param dD	contains the calendar of the pay date to make the discount from the balance. 
	 * @return OrdinaryOutlay, returns a new Ordinary Outlay.
	 */
	public OrdinaryOutlay(String n, long a, Calendar cD,Calendar dD) {
		super(n, a, cD);
		setDiscountDate(dD);
		type = "Regular";
	}


	public Calendar getDiscountDate() {
		return discountDate;
	}

	public void setDiscountDate(Calendar discountDate) {
		this.discountDate = discountDate;
	}

	public String getType() {
		return type;
	}
}
