package model;

import java.util.Date;

public class Loan extends Income{
	private String type = "LOAN";
	private Date payDate;
	private MoneyLender lender;
	
	public Loan(String n, long a, Date cD, Date pD, MoneyLender ml) {
		super(n, a, cD);
		setPayDate(pD);
		setLender(ml);
	}

	public Date getPayDate() {
		return payDate;
	}

	public void setPayDate(Date payDate) {
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
