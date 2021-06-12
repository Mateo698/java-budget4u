package model;

import java.util.Calendar;

/** Represents an extraordinary outlet. 
* @author https://github.com/Mateo698
* @author https://github.com/KennetSanchez
* @version 1.0
*/
public class ExtraordinaryOutlay extends Outlay {

	private static final long serialVersionUID = 1L;
	private String reason;
	private String type;

	/*
	 * Constructor ExtraordinaryOutlay.
	 * This method creates a new Extraordinary outlay. 
	 * @param n 	contains the name of the new outlay. String, not empty neither null.
	 * @param a		contains the amount of the new outlay. Long, cann't be null.
	 * @param cD	contains the calendar of the creation date. Calendar, cann't be null.
	 * @param r		contains the category or reason of the new outlay. 
	 * @return ExtraordinaryOutlay, returns a new ExtraordinaryOutlay.
	 */
	public ExtraordinaryOutlay(String n, long a, Calendar cD, String r) {
		super(n, a, cD);
		setReason(r);
		type = "Irregular";
	}


	public String getReason() {
		return reason;
	}


	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getType() {
		return type;
	}
}
