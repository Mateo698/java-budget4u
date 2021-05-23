package model;

import java.util.Date;

public class RegularIncome extends Income{
	private String type = "REGULAR";
	private Date monthlyDate;
	
	public RegularIncome(String n, long a, Date cD, Date mD) {
		super(n,a,cD);
		setMonthlyDate(mD);
	}

	public Date getMonthlyDate() {
		return monthlyDate;
	}

	public void setMonthlyDate(Date monthlyDate) {
		this.monthlyDate = monthlyDate;
	}

	public String getType() {
		return type;
	}
	
	
}
