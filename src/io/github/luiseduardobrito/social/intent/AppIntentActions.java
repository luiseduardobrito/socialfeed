/**
 * 
 */
package io.github.luiseduardobrito.social.intent;

/**
 * @author Luis Eduardo Brito
 * 
 */
public enum AppIntentActions {
	MESSAGE_ARRIVED("MESSAGE_ARRIVED");

	private static final String packageName = "io.github.luiseduardobrito.social";

	private String action;

	private AppIntentActions(String action) {
		this.action = action;
	}

	public String getFullActionName() {
		return packageName + "." + this.action;
	}

	public String getActionName() {
		return this.action;
	}

	@Override
	public String toString() {
		return getFullActionName();
	}
}
