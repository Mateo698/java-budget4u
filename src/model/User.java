package model;

import java.util.ArrayList;
import java.util.Calendar;

public abstract class User {
	String name, password;
	long currentMoney;
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
		currentMoney = 0;
	}
	
	public void createIncome(String name, long amount,Calendar cD, Calendar monthly) {
		RegularIncome in = new RegularIncome(name, amount, cD, monthly);
		addIncome(in);
	}
	
	public void createIncome(String name, long amount,Calendar cD, String purpose) {
		IrregularIncome in = new IrregularIncome(name, amount, cD, purpose);
		addIncome(in);
	}
	
	public void createIncome(String name, long amount,Calendar cD, MoneyLender lender, Calendar payDay) {
		Loan lo = new Loan(name, amount, cD, payDay, lender);
		addIncome(lo);
	}
	
	public void addIncome(Income income) {
		currentMoney += income.getAmount();
		if(firstIncome == null) {
			firstIncome = new IncomeNode(income.getName(),income);
		}else {
			firstIncome.addNode(firstIncome);
		}
	}
	
	public void removeIncome(Income income) {
		//firstIncome;
	}
	
	public ArrayList<MoneyLender> getMoneyLenders(){
		ArrayList<MoneyLender> list = new ArrayList<MoneyLender>();
		if(firstMoneyLender != null) {
			list.add(firstMoneyLender);
			MoneyLender aux = firstMoneyLender.getNext();
			while(aux != null) {
				list.add(aux);
				aux = aux.getNext();
			}
			return list;
		}else {
			return null;
		}
	}
	
	public ArrayList<Income> getIncomes(){
		if(firstIncome != null) {
			return firstIncome.realIncomes();
		}else {
			return null;
		}
	}
	
	//------------------------------------- getters ------------------------------
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
		return currentMoney;
	}
	
}
