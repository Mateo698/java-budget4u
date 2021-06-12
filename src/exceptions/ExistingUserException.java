package exceptions;

/** It's an exception thrown when the user tries to create another user with a name that it's already registered.
* @author https://github.com/Mateo698
* @author https://github.com/KennetSanchez
* @version 1.0
*/
public class ExistingUserException extends Exception{
	private String msg = "";
	private static final long serialVersionUID = 1;
	
	/** Constructor ExistingUserException.
	 * This method creates a new exception if the user tries to create a new user with a registered name.
	 * @return ExistingUserException, returns a new exception.
	 */
	public ExistingUserException() {
		msg = "There's an user with this name already. Please, try with another name.";
	}
	
	public String getMsg() {
		return msg;
	}
}
