/**
 * 
 */
package io.github.luiseduardobrito.social.exception;

/**
 * @author Luis Eduardo Brito
 * 
 */
public enum AppExceptionLevel {
	WARNING("warning"), ERROR("error"), FATAL("fatal");

	private String label;

	private AppExceptionLevel(String label) {
		this.label = label;
	}

	public String getLabel() {
		return this.label;
	}

	@Override
	public String toString() {
		return getLabel();
	}
}
