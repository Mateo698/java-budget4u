package model;

import java.util.Comparator;

/** It's a comparator for the names of the incomes.
* @author https://github.com/Mateo698
* @author https://github.com/KennetSanchez
* @version 1.0
*/
public class IncomeNameComparator implements Comparator<Income>{

	/*
	 * Compare income.
	 * This method compares the name of two incomes, and picks which one should go first.. 
	 * @param i1 	contains the first income to compare. Income, cann't be null.
	 * @param i2	contains the second income to compare. Income, cann't be null.
	 * @return comparison, returns an integer to decide which one should go first.
	 */
	@Override
	public int compare(Income i1, Income i2) {
		int comparison = i1.getName().compareTo(i2.getName());
		return comparison;
	}

}
