package model;

import java.io.Serializable;

/** It's a checker for the loans.
* @author https://github.com/Mateo698
* @author https://github.com/KennetSanchez
* @version 1.0
*/
public class LoanChecker implements Serializable{
	
	private static final long serialVersionUID = 1;
	private Loan loan;
	
	/** Constructor MoneyLender.
	 * This method creates a new money lender.
	 * @param name		contains the loan to check. Loan cann't be  null
	 * @return LoanChecker, returns a new loan checker..
	 */
	public LoanChecker(Loan l) {
		setLoan(l);
	}

	public Loan getLoan() {
		return loan;
	}

	public void setLoan(Loan loan) {
		this.loan = loan;
	}
}
