package exceptions;

/** It's an exception thrown when the user writes a negative amount.
* @author https://github.com/Mateo698
* @author https://github.com/KennetSanchez
* @version 1.0
*/
public class NegativeAmountException extends Exception{

	private static final long serialVersionUID = 1;
	
	private long amount;
	private final long minimumAmount = 0;
	
	/** Constructor NegativeAmountException.
	 * This method creates a new exception if the user writes a negative numeric value.
	 * @param amount contains the amount that the exception should have. Long, cann't be null.
	 * @return NegativeNumberException, returns a new exception.
	 */
	public NegativeAmountException(long amount) {
		this.amount = amount;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public long getMinimumAmount() {
		return minimumAmount;
	}

}
