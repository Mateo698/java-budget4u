package model;

import java.util.Calendar;

public class OrdinaryOutlay extends Outlay{
	private Calendar discountDate;

	public OrdinaryOutlay(String n, long a, Calendar cD,Calendar dD) {
		super(n, a, cD);
		setDiscountDate(dD);
	}

	public Calendar getDiscountDate() {
		return discountDate;
	}

	public void setDiscountDate(Calendar discountDate) {
		this.discountDate = discountDate;
	}

}
