package model;

import java.util.Date;

public class Income {
	private String name;
	private long amount;
	private Date creationDate;
	
	public Income(String n, long a, Date cD) {
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

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
}
