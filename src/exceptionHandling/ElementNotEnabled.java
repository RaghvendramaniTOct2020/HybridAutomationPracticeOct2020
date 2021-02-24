package exceptionHandling;

public class ElementNotEnabled extends RuntimeException{
	private String message;

	public ElementNotEnabled(String message) {
		super(message);
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
