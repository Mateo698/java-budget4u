package model;

public class MoneyLender {
	MoneyLender nextMoneyLender;
	
	String name;
	User borrower;
	
	public MoneyLender(String name, User borrower) {
		this.name = name;
		this.borrower = borrower;
	}
	
	public MoneyLender getNext() {
		return nextMoneyLender;
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
}
