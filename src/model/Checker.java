package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

/** It's a checker of all the amounts.
* @author https://github.com/Mateo698
* @author https://github.com/KennetSanchez
* @version 1.0
*/
public class Checker implements Serializable{
	private static final long serialVersionUID = 1;
	private ArrayList<IncomeChecker> incomesCheck;
	private ArrayList<OutlayChecker> outlaysCheck;
	private ArrayList<LoanChecker> loansCheck;
	
	
	 /** Constructor Checker.
	 * This method creates a new checker, and initialize the arrays. 
	 * @return Checker, returns a new Checker.
	 */
	public Checker() {
		setIncomesCheck(new ArrayList<>());
		setOutlaysCheck(new ArrayList<>());
		setLoansCheck(new ArrayList<>());
	}
	
	
	/** Check incomes.
	* This method checks the total amount of the incomes in a wanted date. 
	* @param c 	contains the wanted date to check all the incomes. Calendar, cann't be null.
	* @return amount, returns a long with the total amount of all the incomes.
	*/
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
	
	/** Check outlays.
	 * This method checks the total amount of the outlays in a wanted date. 
	 * @param c 	contains the wanted date to check all the outlays. Calendar, cann't be null.
	 * @return amount, returns a long with the total amount of all the outlays.
	 */
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
	
	/** Check loans.
	 * This method checks the total amount of the loans in a wanted date. 
	 * @param c 	contains the wanted date to check all the loans. Calendar, cann't be null.
	 * @return amount, returns a long with the total amount of all the loans.
	 */
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
	
	
	/** Pay date.
	 * This method verify if it's the pay date of the loan. 
	 * @param loanChecker 	contains the loan checker to verify. LoanChecker, cann't be null.
	 * @param c				contains the date of the pay date of the loan. Calendar, cann't be null.
	 * @return toPay, returns a boolean to represent if it's time to pay or not.
	 */
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


	/** Add income checker.
	 * This method adds a new income checker to the array. 
	 * @param ic 	contains the income checker to add. IncomeChecker, cann't be null.
	 */
	public void addIncomeChecker(IncomeChecker ic) {
		incomesCheck.add(ic);
	}
	
	/** Add outlay checker.
	 * This method adds a new outlay checker to the array. 
	 * @param ic 	contains the outlay checker to add. OutlayChecker, cann't be null.
	 */
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



	/** Remove income.
	 * This method removes a regular income from the array. 
	 * @param income	contains the income to remove. RegularIncome, cann't be null.
	 */
	public void removeIncome(RegularIncome income) {
		boolean end = false;
		for (int i = 0; i < incomesCheck.size() && !end; i++) {
			if(incomesCheck.get(i).getIncome() == income) {
				incomesCheck.remove(i);
				end = true;
			}
		}
	}
	
	/** Remove outlay.
	 * This method removes a ordinary outlay from the array. 
	 * @param outlay	contains the outlay to remove. OrdinaryOutlay, cann't be null.
	 */
	public void removeOutlay(OrdinaryOutlay outlay) {
		boolean end = false;
		for (int i = 0; i < outlaysCheck.size() && !end; i++) {
			if(outlaysCheck.get(i).getOutlay() == outlay) {
				outlaysCheck.remove(i);
				end = true;
			}
		}
	}
	
	/** Remove loan.
	 * This method removes a loan from the array. 
	 * @param income	contains the loan to remove. Loan, cann't be null.
	 */
	public void removeLoan(Loan loan) {
		boolean end = false;
		for (int i = 0; i < loansCheck.size() && !end; i++) {
			if(loansCheck.get(i).getLoan() == loan) {
				end = true;
				loansCheck.remove(i);
			}
		}
	}
	
	/** Edit income.
	 * This method edits a regular income. 
	 * @param oldIn	contains the income the old data. RegularIncome, cann't be null.
	 * @param newIn	contains the income the new data. RegularIncome, cann't be null.
	 */
	public void editIncome(RegularIncome oldIn, RegularIncome newIn) {
		boolean end = false;
		for (int i = 0; i < incomesCheck.size() && !end; i++) {
			if(incomesCheck.get(i).getIncome() == oldIn) {
				incomesCheck.get(i).setIncome(newIn);
				end = true;
			}
		}
	}
	
	/** Edit outlay.
	 * This method edits a ordinary outlay. 
	 * @param oldOut	contains the outlay with the old data. OrdinaryOutlay, cann't be null.
	 * @param newOut	contains the outlay with the new data. OrdinaryOutlay, cann't be null.
	 */
	public void editOutlay(OrdinaryOutlay oldOut, OrdinaryOutlay newOut) {
		boolean end = false;
		for (int i = 0; i < incomesCheck.size() && !end; i++) {
			if(outlaysCheck.get(i).getOutlay() == oldOut) {
				outlaysCheck.get(i).setOutlay(newOut);
				end = true;
			}
		}
	}
	
	/** Edit loan.
	 * This method edits a loan. 
	 * @param oldLoan	contains the loan the old data. Loan, cann't be null.
	 * @param newLoan	contains the loan the new data. Load, cann't be null.
	 */
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
