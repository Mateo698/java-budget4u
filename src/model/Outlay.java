package model;

import java.io.Serializable;
import java.util.Calendar;

public class Outlay implements Comparable<Outlay>,Serializable{
	
	private static final long serialVersionUID = 1;
	private String name;
	private long amount;
	private Calendar creationDate;
	
	public Outlay(String n, long a, Calendar cD) {
		setName(n);
		setAmount(-a);
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
	public int compareTo(Outlay o) {
		return creationDate.compareTo(o.getCreationDate());
	}
	
}
