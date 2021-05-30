package model;

import java.util.Calendar;

public class IrregularIncome extends Income{
	private String type = "IRREGULAR";
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
