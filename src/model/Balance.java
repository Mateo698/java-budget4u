package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class Balance implements Serializable{
	
	private static final long serialVersionUID = 1;
	private Calendar date;
	private double income, outlay, loan, total;
	private String name;
	private Balance nextBalance;
	
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
	
	public void addBalance(Balance newBalance) {
		if(nextBalance == null) {
			nextBalance = newBalance;
		}else {
			nextBalance.addBalance(newBalance);
		}
	}
	
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
	
}
