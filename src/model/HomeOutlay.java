package model;

import java.util.Calendar;

public class HomeOutlay extends Outlay{

	private static final long serialVersionUID = 1L;
	private String type = "Home";
	private String category;
	
	public HomeOutlay(String n, long a, Calendar cD, String c) {
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
