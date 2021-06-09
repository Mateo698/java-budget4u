package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

public class Checker implements Serializable{
	private static final long serialVersionUID = 1;
	private ArrayList<IncomeChecker> incomesCheck;
	private ArrayList<OutlayChecker> outlaysCheck;
	private ArrayList<LoanChecker> loansCheck;
	
	public Checker() {
		setIncomesCheck(new ArrayList<>());
		setOutlaysCheck(new ArrayList<>());
		setLoansCheck(new ArrayList<>());
	}
	
	

	public void checkIncomes(Calendar c) {
		long amount = 0;
		for (int i = 0; i < incomesCheck.size(); i++) {
			if(incomesCheck.get(i).getIncome().getMonthlyDate().get(Calendar.DAY_OF_MONTH) <= c.get(Calendar.DAY_OF_MONTH) && !incomesCheck.get(i).isChecked()) {
				amount += incomesCheck.get(i).getIncome().getAmount();
				incomesCheck.get(i).setChecked();
			}
		}
		
	}
	
	//checkLoans
	
	//checkOutlays
	
	public void addIncomeChecker(IncomeChecker ic) {
		incomesCheck.add(ic);
	}
	
	public void addOutlayChecker(OutlayChecker oc) {
		outlaysCheck.add(oc);
	}

	public ArrayList<IncomeChecker> getIncomesCheck() {
		return incomesCheck;
	}

	public void setIncomesCheck(ArrayList<IncomeChecker> incomesCheck) {
		this.incomesCheck = incomesCheck;
	}

	public ArrayList<OutlayChecker> getOutlaysCheck() {
		return outlaysCheck;
	}

	public void setOutlaysCheck(ArrayList<OutlayChecker> outlaysCheck) {
		this.outlaysCheck = outlaysCheck;
	}
	
	public ArrayList<LoanChecker> getLoansCheck() {
		return loansCheck;
	}

	public void setLoansCheck(ArrayList<LoanChecker> loansCheck) {
		this.loansCheck = loansCheck;
	}



	public void removeIncome(RegularIncome income) {
		boolean end = false;
		for (int i = 0; i < incomesCheck.size() && !end; i++) {
			if(incomesCheck.get(i).getIncome() == income) {
				incomesCheck.remove(i);
				end = true;
			}
		}
	}
	
	public void editIncome(RegularIncome oldIn, RegularIncome newIn) {
		boolean end = false;
		for (int i = 0; i < incomesCheck.size() && !end; i++) {
			if(incomesCheck.get(i).getIncome() == oldIn) {
				incomesCheck.get(i).setIncome(newIn);
				end = true;
			}
		}
	}
}
