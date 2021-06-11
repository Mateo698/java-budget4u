package model;

import java.util.Comparator;

public class OutlayNameComparator implements Comparator<Outlay>{

	@Override
	public int compare(Outlay o1, Outlay o2) {
		return o1.getName().compareTo(o2.getName());
	}

}
