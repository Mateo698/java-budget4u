package model;

public class IncomeChecker {
	private RegularIncome income;
	private boolean checked;
	
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
