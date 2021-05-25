package model;

import java.util.Date;

public class OrdinaryOutlay extends Outlay{
	private Date discountDate;

	public OrdinaryOutlay(String n, long a, Date cD,Date dD) {
		super(n, a, cD);
		setDiscountDate(dD);
	}

	public Date getDiscountDate() {
		return discountDate;
	}

	public void setDiscountDate(Date discountDate) {
		this.discountDate = discountDate;
	}

}
