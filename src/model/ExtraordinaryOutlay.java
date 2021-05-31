package model;

import java.util.Calendar;

public class ExtraordinaryOutlay extends Outlay {
	private String reason;
	

	public ExtraordinaryOutlay(String n, long a, Calendar cD, String r) {
		super(n, a, cD);
		setReason(r);
	}


	public String getReason() {
		return reason;
	}


	public void setReason(String reason) {
		this.reason = reason;
	}

}
