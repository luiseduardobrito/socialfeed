/**
 * 
 */
package io.github.luiseduardobrito.social.exception;

import com.parse.ParseException;

/**
 * @author Luis Eduardo Brito
 * 
 */
public class AppParseException extends ParseException {

	private static final long serialVersionUID = 6546015654163835830L;

	/**
	 * @param theCode
	 * @param theMessage
	 */
	public AppParseException(int theCode, String theMessage) {
		super(theCode, theMessage);
	}

	/**
	 * @param e
	 * @return
	 */
	public static AppParseException fromParse(ParseException e) {
		return new AppParseException(e.getCode(), e.getMessage());
	}
}
