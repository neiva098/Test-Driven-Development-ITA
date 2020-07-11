package camelCase;

@SuppressWarnings("serial")
public class SpecialCharException extends RuntimeException {
	public SpecialCharException(String msg) {
		super(msg);
	}
}
