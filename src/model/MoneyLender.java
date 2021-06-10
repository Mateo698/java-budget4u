package model;

public class MoneyLender {
	MoneyLender nextMoneyLender;
	
	String name, lastName, phone;
	User borrower;
	
	public MoneyLender(String name, String lastName, String phone, User borrower) {
		this.name = name;
		this.lastName = lastName;
		this.phone = phone;
		this.borrower = borrower;
	}
	
	public MoneyLender getNext() {
		return nextMoneyLender;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setNext(MoneyLender next) {
		nextMoneyLender = next;
	}
	
	public String getName() {
		return name;
	}
	
	public User getBorrower() {
		return borrower;
	}
	
	public void addMoneyLender(MoneyLender ml) {
		if(nextMoneyLender == null) {
			nextMoneyLender = ml;
		}else {
			nextMoneyLender.addMoneyLender(ml);
		}
	}
}
