package model;

import java.io.Serializable;
import java.util.Calendar;

public class RegularIncome extends Income implements Serializable{
	
	private static final long serialVersionUID = 1;
	private String type = "Regular";
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
