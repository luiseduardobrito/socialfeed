/**
 * 
 */
package io.github.luiseduardobrito.exception;

/**
 * @author Luis Eduardo Brito
 * 
 */
public class AppException extends Exception {

	private static final long serialVersionUID = 6115094172326797956L;

	private AppExceptionLevel level;

	public AppException(AppExceptionLevel level) {
		this.level = level;
	}
	
	public AppExceptionLevel getLevel() {
		return this.level;
	}
}
