package model;

import java.util.Comparator;

public class OutlayNameComparator implements Comparator<Outlay>{

	/*
	 * Compare outlay.
	 * This method compares the name of two outlays, and picks which one should go first.. 
	 * @param o1 	contains the first outlay to compare. Outlay, cann't be null.
	 * @param o2	contains the second outlay to compare. Outlay, cann't be null.
	 * @return comparison, returns an integer to decide which one should go first.
	 */
	@Override
	public int compare(Outlay o1, Outlay o2) {
		int comparison = o1.getName().compareTo(o2.getName());
		return comparison;
	}
	
}
