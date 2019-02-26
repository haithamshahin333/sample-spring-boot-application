package puckboard.sample.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ResourceExistsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ResourceExistsException() {
		super();
	}

	public ResourceExistsException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ResourceExistsException(String message, Throwable cause) {
		super(message, cause);
	}

	public ResourceExistsException(String message) {
		super(message);
	}

	public ResourceExistsException(Throwable cause) {
		super(cause);
	}

}
