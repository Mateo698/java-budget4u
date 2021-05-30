package model;

import java.util.Calendar;

public class RegularIncome extends Income{
	private String type = "REGULAR";
	private Calendar monthlyDate;
	
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
