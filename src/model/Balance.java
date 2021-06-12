package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

/** Represents the balance(s) of the user. 
* @author https://github.com/Mateo698
* @author https://github.com/KennetSanchez
* @version 1.0
*/ 
public class Balance implements Serializable{
	
	private static final long serialVersionUID = 1;
	private Calendar date;
	private double income, outlay, loan, total;
	private String name;
	private Balance nextBalance;
	
	/** Create a new outlay - regular.
	 * This method creates a new regular outlay. 
	 * @param income 	contains the total amount of incomes. Long, cann't be null.
	 * @param outlay	contains the total amount of outlays. Long, cann't be null.
	 * @param loans		contains the total amount of loans. Long, cann't be null.
	 * @param date		contains the creation date of the balance. Calendar, cann't be null.
	 * @return Balance, return a new balance.
	 */
	public Balance(double income, double outlay, double loans, Calendar date) {
		this.setIncome(income);
		this.setOutlay(outlay);
		this.loan = loans;
		this.date = date;
		
		total = this.income - this.outlay;
		setName(date.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.US)+" "+date.get(Calendar.YEAR));
	}
	
	public Balance getNext() {
		return nextBalance;
	}
	
	public String getStringDate() {
		String balanceDate = date.get(Calendar.DAY_OF_MONTH) + "/" + date.get(Calendar.MONTH) + "/" + date.get(Calendar.YEAR);
		return balanceDate;
	}
	
	public void setNext(Balance next) {
		nextBalance = next;
	}
	
	/** Add balance.
	 * 	This method adds a new balance to the list.
	 * @param newBalance	contains the new balance to add. Balance Cann't be null.
	 */
	public void addBalance(Balance newBalance) {
		if(nextBalance == null) {
			nextBalance = newBalance;
		}else {
			nextBalance.addBalance(newBalance);
		}
	}
	
	/**	To arrayList - first part.
	 * 	This method converts the balances into an array list.
	 * @return list, an array with the other balances added
	 */
	public ArrayList<Balance> toArrayList(){
		ArrayList<Balance> list = new ArrayList<Balance>();
		list.add(this);
		if(nextBalance != null) {
			list = nextBalance.toArrayList(list);
			return list;
		}else {
			return list;
		}
	}
	
	/**	To arrayList - second part.
	 * 	This method converts the balances into an array list.
	 * @param list	contains the array list created in the first method. Cann't be null.
	 * @return list, an array with the other balances added
	 */
	public ArrayList<Balance> toArrayList(ArrayList<Balance> list){
		list.add(this);
		if(nextBalance != null) {
			list = nextBalance.toArrayList(list);
			return list;
		}else {
			return list;
		}
	}

	public double getIncome() {
		return income;
	}

	public void setIncome(double income) {
		this.income = income;
	}

	public double getOutlay() {
		return outlay;
	}

	public void setOutlay(double outlay) {
		this.outlay = outlay;
	}

	public double getLoan() {
		return loan;
	}

	public void setLoan(double loan) {
		this.loan = loan;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int getMonth() {
		return date.get(Calendar.MONTH);
	}
	
	public int getYear() {
		return date.get(Calendar.YEAR);
	}
}
