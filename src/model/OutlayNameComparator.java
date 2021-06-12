package model;

import java.util.Comparator;

/** It's a comparator for the names of the outlays.
* @author https://github.com/Mateo698
* @author https://github.com/KennetSanchez
* @version 1.0
*/
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
