package model;

import java.io.Serializable;

public class LoanChecker implements Serializable{
	
	private static final long serialVersionUID = 1;
	private Loan loan;
	private boolean checked;
	
	public LoanChecker(Loan l) {
		setLoan(l);
		checked = false;
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

	public Loan getLoan() {
		return loan;
	}

	public void setLoan(Loan loan) {
		this.loan = loan;
	}
}
