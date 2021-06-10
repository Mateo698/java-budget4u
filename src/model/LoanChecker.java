package model;

import java.io.Serializable;

public class LoanChecker implements Serializable{
	
	private static final long serialVersionUID = 1;
	private Loan loan;
	
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
