package model;

import java.util.Comparator;

public class IncomeNameComparator implements Comparator<Income>{

	@Override
	public int compare(Income o1, Income o2) {
		return o1.getName().compareTo(o2.getName());
	}

}
