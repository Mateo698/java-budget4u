package exceptions;

public class ExistingUserException extends Exception{
	private String msg = "";
	private static final long serialVersionUID = 1;
	
	public ExistingUserException() {
		msg = "There's an user with this name already. Please, try with another name.";
	}
	
	public String getMsg() {
		return msg;
	}
}
