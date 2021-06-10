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
	
	

	public long checkIncomes(Calendar c) {
		long amount = 0;
		for (int i = 0; i < incomesCheck.size(); i++) {
			if(incomesCheck.get(i).getIncome().getMonthlyDate().get(Calendar.DAY_OF_MONTH) <= c.get(Calendar.DAY_OF_MONTH) && !incomesCheck.get(i).isChecked()) {
				amount += incomesCheck.get(i).getIncome().getAmount();
				incomesCheck.get(i).setChecked();
			}
		}
	
		return amount;
	}
	
	public long checkOutlays(Calendar c) {
		long amount = 0;
		for (int i = 0; i < outlaysCheck.size(); i++) {
			if(outlaysCheck.get(i).getOutlay().getDiscountDate().get(Calendar.DAY_OF_WEEK) <= c.get(Calendar.DAY_OF_WEEK) && !outlaysCheck.get(i).isChecked()) {
				amount -= outlaysCheck.get(i).getOutlay().getAmount();
			}
		}
		
		return amount;
	}
	
	//------------------------------------------------------------------- CHECK LOANS ----------------------------------------------------
	public long checkLoans(Calendar c) {
		long amount = 0;
		for (int i = 0; i < loansCheck.size(); i++) {
			if(payDate(loansCheck.get(i),c)) {
				amount -= loansCheck.get(i).getLoan().getAmount();
				loansCheck.remove(i);
			}
		}
		return amount;
	}
	
	
	private boolean payDate(LoanChecker loanChecker, Calendar c) {
		boolean toPay = false;
		if(loanChecker.getLoan().getPayDate().get(Calendar.MONTH) == c.get(Calendar.MONTH)) {
			if(loanChecker.getLoan().getPayDate().get(Calendar.DAY_OF_MONTH) <= c.get(Calendar.DAY_OF_MONTH)) {
				toPay = true;
			}
		}else if(loanChecker.getLoan().getPayDate().get(Calendar.MONTH) > c.get(Calendar.MONTH)){
			toPay = true;
		}
		return toPay;
	}



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
	
	public void removeOutlay(OrdinaryOutlay outlay) {
		boolean end = false;
		for (int i = 0; i < outlaysCheck.size() && !end; i++) {
			if(outlaysCheck.get(i).getOutlay() == outlay) {
				outlaysCheck.remove(i);
				end = true;
			}
		}
	}
	
	public void removeLoan(Loan loan) {
		boolean end = false;
		for (int i = 0; i < loansCheck.size() && !end; i++) {
			if(loansCheck.get(i).getLoan() == loan) {
				end = true;
				loansCheck.remove(i);
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
	
	public void editOutlay(OrdinaryOutlay oldOut, OrdinaryOutlay newOut) {
		boolean end = false;
		for (int i = 0; i < incomesCheck.size() && !end; i++) {
			if(outlaysCheck.get(i).getOutlay() == oldOut) {
				outlaysCheck.get(i).setOutlay(newOut);
				end = true;
			}
		}
	}
	
	public void editLoan(Loan oldLoan, Loan newLoan) {
		boolean end = false;
		for (int i = 0; i < loansCheck.size() && !end; i++) {
			if(loansCheck.get(i).getLoan() == oldLoan) {
				loansCheck.get(i).setLoan(newLoan);
				end = true;
			}
		}
	}
}
