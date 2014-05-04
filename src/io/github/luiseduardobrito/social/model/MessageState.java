/**
 * 
 */
package io.github.luiseduardobrito.social.model;

import io.github.luiseduardobrito.social.R;

/**
 * @author Luis Eduardo Brito
 * 
 */
public enum MessageState {

	SENT("sent", R.drawable.bg_points_circle),
	RECEIVED("received", R.drawable.bg_points_circle),
	OPENED("opened", R.drawable.bg_points_circle_disabled),
	ANSWERED("answered", R.drawable.bg_points_circle_disabled),
	GONE("gone", R.drawable.bg_points_circle_disabled, false);

	private String name;
	private int resource;
	private Boolean visible;

	private MessageState(String name, int resourceId) {
		this.name = name;
		this.resource = resourceId;
		this.visible = true;
	}

	private MessageState(String name, int resourceId, Boolean visibility) {
		this.name = name;
		this.resource = resourceId;
		this.visible = visibility;
	}

	public String getName() {
		return this.name;
	}

	@Override
	public String toString() {
		return getName();
	}

	public int getResourceId() {
		return this.resource;
	}

	public Boolean isVisible() {
		return this.visible;
	}
	
	public static MessageState fromString(String text) {
		if (text != null) {
			for (MessageState b : MessageState.values()) {
				if (text.equalsIgnoreCase(b.name)) {
					return b;
				}
			}
		}
		return null;
	}
}