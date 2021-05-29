package exceptions;

public class NotOnlyNumberException extends Exception{

	private final long serialVersionUID = 1;
	private String text;
	
	public NotOnlyNumberException(String t) {
		text = t;
	}

	public String getText() {
		return text;
	}


}
