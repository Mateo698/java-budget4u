package model;

import java.util.Date;

public class HomeOutlay extends Outlay{
	private String type = "HOME";
	private String category;
	
	public HomeOutlay(String n, long a, Date cD, String c) {
		super(n, a, cD);
		category = c;
	}

	public String getType() {
		return type;
	}


	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
}
