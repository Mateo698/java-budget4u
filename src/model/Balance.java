package model;

import java.util.Calendar;
import java.util.Date;

public class Balance {
	private Calendar date;
	private double income, outlay, loans, total;
	private String balanceDate;
	private Balance nextBalance;
	
	public Balance(double income, double outlay, double loans, Calendar date) {
		this.income = income;
		this.outlay = outlay;
		this.loans = loans;
		this.date = date;
		balanceDate = date.get(Calendar.DAY_OF_MONTH) + "/" + date.get(Calendar.MONTH) + "/" + date.get(Calendar.YEAR);
	}
	
	public Balance getNext() {
		return nextBalance;
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
}
