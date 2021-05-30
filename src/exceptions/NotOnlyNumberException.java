package exceptions;

public class NotOnlyNumberException extends Exception{

	private static final long serialVersionUID = 1;
	private String text;
	
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
