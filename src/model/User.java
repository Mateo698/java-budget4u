package model;

import java.util.Date;

public abstract class User {
	String name, password;
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
	}
	
	public void createIncome(String name, long amount, int type,Date cD, Date monthly) {
		switch(type) {
			case 1://REGULAR
				RegularIncome income = new RegularIncome(name, amount, cD, monthly);
				addIncome(income);
			break;
		}
	}
	
	public void addIncome(Income income) {
		if(firstIncome == null) {
			firstIncome = new IncomeNode(income.getName(),income);
		}else {
			firstIncome.addNode(firstIncome);
		}
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
}
