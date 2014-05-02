/**
 * 
 */
package io.github.luiseduardobrito.social.model;

import io.github.luiseduardobrito.social.R;

/**
 * @author Luis Eduardo Brito
 * 
 */
public enum MessageType {
	PHOTO("photo", R.drawable.photo33), VIDEO("video", R.drawable.play39), CHECKLIST(
			"checklist",
			R.drawable.check30), INPUTLIST("inputlist", R.drawable.list30);

	private String name;
	private int resource;

	private MessageType(String name, int resourceId) {
		this.name = name;
		this.resource = resourceId;
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
}
