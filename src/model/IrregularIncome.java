package model;

import java.io.Serializable;
import java.util.Calendar;

public class IrregularIncome extends Income implements Serializable{
	
	private static final long serialVersionUID = 1;
	private String type = "Irregular";
	private String purpose;
	
	public IrregularIncome(String n, long a, Calendar cD, String p) {
		super(n, a, cD);
		setPurpose(p);
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getType() {
		return type;
	}
}
