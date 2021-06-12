package model;

import java.io.Serializable;
import java.util.Calendar;

/** It's a comparator for the names of the outlays.
* @author https://github.com/Mateo698
* @author https://github.com/KennetSanchez
* @version 1.0
*/
public class IrregularIncome extends Income implements Serializable{
	
	private static final long serialVersionUID = 1;
	private String type = "Irregular";
	private String purpose;
	
	 /** Constructor IrregularIncome.
	 * This method creates a new irregular income. 
	 * @param n 	contains the name of the new income. String, cann't be empty neither null.
	 * @param a		contains the amount of the of the income. Long, cann't be null.
	 * @param cD	contains the creation date of the income. Calendar, cann't be null, neither a future date.
	 * @param p		contains the reason of this new income. String, cann't be null.
	 * @return IrregularIncome, returns a new irregular income.
	 */
	public IrregularIncome(String n, long a, Calendar cD, String p) {
		super(n, a, cD);
		setPurpose(p);
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getType() {
		return type;
	}
}
