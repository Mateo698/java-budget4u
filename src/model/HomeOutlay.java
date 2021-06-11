package model;

import java.util.Calendar;

public class HomeOutlay extends Outlay {

	private static final long serialVersionUID = 1L;
	private String type = "Home";
	private String category;

	/*
	 * Constructor HomeOutlay.
	 * This method creates a Home Outlay. 
	 * @param n 	contains the name of the new outlay. String, not empty neither null.
	 * @param a		contains the amount of the new outlay. Long, cann't be null.
	 * @param cD	contains the calendar of the creation date. Calendar, cann't be null.
	 * @param c		contains the category or reason of the new outlay. 
	 * @return HomeOutlay, returns a new HomeOutlay.
	 */
	public HomeOutlay(String n, long a, Calendar cD, String c) {
		super(n, a, cD);
		category = c;
	}

	public String getType() {
		return type;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
}
