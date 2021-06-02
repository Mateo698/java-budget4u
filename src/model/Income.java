package model;

import java.util.Calendar;

public class Income implements Comparable<Income>{
	private String name;
	private long amount;
	private Calendar creationDate;
	
	public Income(String n, long a, Calendar cD) {
		setName(n);
		setAmount(a);
		setCreationDate(cD);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public Calendar getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Calendar creationDate) {
		this.creationDate = creationDate;
	}
	
	public String getDate() {
		String realDate = "";
		realDate = creationDate.get(Calendar.DAY_OF_MONTH) + "/" + creationDate.get(Calendar.MONTH) + "/" + creationDate.get(Calendar.YEAR);
		return realDate;
	}

	@Override
	public int compareTo(Income o) {
		return creationDate.compareTo(o.getCreationDate());
	}
}
