package model;

import java.util.Calendar;

public class OrdinaryOutlay extends Outlay{

	private static final long serialVersionUID = 1L;
	private Calendar discountDate;
	private String type;
	public OrdinaryOutlay(String n, long a, Calendar cD,Calendar dD) {
		super(n, a, cD);
		setDiscountDate(dD);
		type = "Regular";
	}


	public Calendar getDiscountDate() {
		return discountDate;
	}

	public void setDiscountDate(Calendar discountDate) {
		this.discountDate = discountDate;
	}

	public String getType() {
		return type;
	}
}
