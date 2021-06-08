package model;

import java.util.Calendar;

public class ExtraordinaryOutlay extends Outlay {

	private static final long serialVersionUID = 1L;
	private String reason;
	private String type;

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
