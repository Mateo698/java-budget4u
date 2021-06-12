package model;

import java.io.Serializable;

/** Represents a money lender, needed to add a new lender.
* @author https://github.com/Mateo698
* @author https://github.com/KennetSanchez
* @version 1.0
*/
public class MoneyLender implements Serializable{

	private static final long serialVersionUID = 1;

	MoneyLender nextMoneyLender;
	
	String name, lastName, phone;
	User borrower;
	
	
	 /** Constructor MoneyLender.
	 * This method creates a new money lender.
	 * @param name		contains the name of the money lender. String cann't be empty neither null
	 * @param lastName	contains the last name of the money lender. String cann't be empty neither null.
	 * @return MoneyLender, returns a new money lender.
	 */
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
	
	/** Add MoneyLender.
	 * This method adds a money lender to the list.
	 * @param ml	contains the money lender to add. MoneyLender, cann't be null.
	 */
	public void addMoneyLender(MoneyLender ml) {
		if(nextMoneyLender == null) {
			nextMoneyLender = ml;
		}else {
			nextMoneyLender.addMoneyLender(ml);
		}
	}
}
