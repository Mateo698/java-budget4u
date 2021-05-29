package model;

import java.util.Date;

public abstract class User {
	String name, password;
	long actualMoney;
	TypesOfUser type;
	Balance firstBalance;
	MoneyLender firstMoneyLender;
	IncomeNode firstIncome;
	OutlayNode firstOutlay;
	
	public User(String uname, String upass, TypesOfUser utype) {
		name = uname;
		password = upass;
		type = utype;
		firstIncome = null;
		firstOutlay = null;
		actualMoney = 0;
	}
	
	public void createIncome(String name, long amount,Date cD, Date monthly) {
		
	}
	
	public void addIncome(Income income) {
		if(firstIncome == null) {
			firstIncome = new IncomeNode(income.getName(),income);
		}else {
			firstIncome.addNode(firstIncome);
		}
	}
	
	public void removeIncome(Income income) {
		//firstIncome;
	}
	
	public IncomeNode getIncomeNode() {
		return firstIncome;
	}
	
	public OutlayNode getOutlayNode() {
		return firstOutlay;
	}
	
	public String getName() {
		return name;
	}
	
	public String getPassword() {
		return password;
	}
	
	public TypesOfUser getType() {
		return type;
	}
	
	public long getMoney() {
		return actualMoney;
	}
	
}
