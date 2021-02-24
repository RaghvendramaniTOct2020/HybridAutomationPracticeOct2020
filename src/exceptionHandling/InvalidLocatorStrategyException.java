package exceptionHandling;

public class InvalidLocatorStrategyException extends RuntimeException{
	private String message;

	public InvalidLocatorStrategyException(String message) {
		super(message);
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
