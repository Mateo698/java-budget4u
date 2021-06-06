package model;

import java.util.Calendar;

public class Loan extends Income{
	private String type = "Loan";
	private Calendar payDate;
	private MoneyLender lender;
	
	public Loan(String n, long a, Calendar cD, Calendar pD, MoneyLender ml) {
		super(n, a, cD);
		setPayDate(pD);
		setLender(ml);
	}

	public Calendar getPayDate() {
		return payDate;
	}

	public void setPayDate(Calendar payDate) {
		this.payDate = payDate;
	}

	public MoneyLender getLender() {
		return lender;
	}

	public void setLender(MoneyLender lender) {
		this.lender = lender;
	}

	public String getType() {
		return type;
	}
}
