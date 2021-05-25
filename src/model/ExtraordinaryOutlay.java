package model;

import java.util.Date;

public class ExtraordinaryOutlay extends Outlay {
	private String reason;
	

	public ExtraordinaryOutlay(String n, long a, Date cD,String r) {
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
