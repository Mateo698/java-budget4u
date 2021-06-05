package model;

import java.util.Date;

import org.junit.jupiter.params.shadow.com.univocity.parsers.conversions.CalendarConversion;

public class Balance {
	Date date = new Date();
	double income, outlay, loans, total;
	String balanceDate;
	
	Balance nextBalance;
	
	public Balance(double income, double outlay, double loans) {
		this.income = income;
		this.outlay = outlay;
		this.loans = loans;
				
		balanceDate = (date.getYear()+1900) + "-" + (date.getMonth()+1) + "-" + date.getDate() + "-" + date.getHours() + "-" +date.getMinutes();
	}
	
	public Balance getNext() {
		return nextBalance;
	}
	
	public void setNext(Balance next) {
		nextBalance = next;
	}
}
