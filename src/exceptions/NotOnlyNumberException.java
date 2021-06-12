package exceptions;

/** It's an exception thrown when the user writes a number value with characters which aren't numbers.
* @author https://github.com/Mateo698
* @author https://github.com/KennetSanchez
* @version 1.0
*/
public class NotOnlyNumberException extends Exception{

	private static final long serialVersionUID = 1;
	private String text;
	
	/** Constructor NotOnlyNumberException.
	 * This method creates a new exception if the user writes a numeric value with characters different of numbers.
	 * @param t contains the text that the exception should have. String, cann't be empty neither null.
	 * @return NotOnlyNumberException, returns a new exception.
	 */
	public NotOnlyNumberException(String t) {
		text = t;
	}

	public String getText() {
		return text;
	}
	
	public void setText(String t)
	{
		text = t;
	}

}
