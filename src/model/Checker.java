package model;

import java.util.ArrayList;
import java.util.Calendar;

public class Checker {
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
			if(incomesCheck.get(i).getIncome().getMonthlyDate().compareTo(c)==0) {
				amount += incomesCheck.get(i).getIncome().getAmount();
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
}
