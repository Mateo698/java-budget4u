package model;

import java.io.Serializable;

/** It's a checker for the incomes.
* @author https://github.com/Mateo698
* @author https://github.com/KennetSanchez
* @version 1.0
*/
public class IncomeChecker implements Serializable{
	
	private static final long serialVersionUID = 1;
	private RegularIncome income;
	private boolean checked;
	
	/** Constructor IncomeChecker.
	 * This method creates a new income. 
	 * @param income 	contains the income to check. Income, cann't be null.
	 * @return IncomeChecler, 	returns an income checker to let the program knows when an income should be added.
	 */
	public IncomeChecker(RegularIncome income) {
		this.setIncome(income);
		checked = false;
	}

	public RegularIncome getIncome() {
		return income;
	}

	public void setIncome(RegularIncome income) {
		this.income = income;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked() {
		if(checked) {
			checked = false;
		}else {
			checked = true;
		}
	}
	
	
}
