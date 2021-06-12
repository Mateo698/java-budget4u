package model;

import java.io.Serializable;
import java.util.Calendar;

/** It's a comparator for the names of the outlays.
* @author https://github.com/Mateo698
* @author https://github.com/KennetSanchez
* @version 1.0
*/
public class Loan extends Income implements Serializable{
	private static final long serialVersionUID = 1;
	private String type = "Loan";
	private Calendar payDate;
	private MoneyLender lender;
	
	/** Constructor Loan.
	 * This method creates a new loan. There should be at least one money lender. 
	 * @param n 	contains the name of the new loan. String, cann't be empty neither null.
	 * @param a		contains the amount of the of the loan. Long, cann't be null.
	 * @param cD	contains the creation date of the loan. Calendar, cann't be null, neither a future date.
	 * @param pD	contains the pay date of this loan. Calendar, cann't be null.
	 * @param ml	contains the money lender who lends this money.
	 * @return IrregularIncome, returns a new irregular income.
	 */
	public Loan(String n, long a, Calendar cD, Calendar pD, MoneyLender ml) {
		super(n, a, cD);
		setPayDate(pD);
		setLender(ml);
	}

	public Calendar getPayDate() {
		return payDate;
	}

	public void setPayDate(Calendar payDate) {
		this.payDate = payDate;
	}

	public MoneyLender getLender() {
		return lender;
	}

	public void setLender(MoneyLender lender) {
		this.lender = lender;
	}

	public String getType() {
		return type;
	}
}
