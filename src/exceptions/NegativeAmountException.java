package exceptions;

public class NegativeAmountException extends Exception{

	private static final long serialVersionUID = 1;
	
	private long amount;
	private final long minimumAmount = 0;
	
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
