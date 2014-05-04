/**
 * 
 */
package io.github.luiseduardobrito.social.fragment;

import android.app.Fragment;

/**
 * @author Luis Eduardo Brito
 * 
 */
public enum AppFragment {

	PLACEHOLDER(0), SETTINGS(1, "Settings");

	public static AppFragment fromPosition(Integer position) {
		if (position != null) {
			for (AppFragment b : AppFragment.values()) {
				if (position.equals(b.position)) {
					return b;
				}
			}
		}
		return null;
	}

	public static Fragment build(Integer position) {

		AppFragment fragmentEnum = fromPosition(position);

		if (fragmentEnum.equals(AppFragment.PLACEHOLDER)) {
			return PlaceholderFragment_.newInstance(position);
		}

		return null;
	}

	private String title;
	private Integer position;

	private AppFragment(Integer position, String title) {
		this.position = position;
		this.title = title;
	}

	private AppFragment(Integer position) {
		this.position = position;
		this.title = "";
	}

	public Integer getPosition() {
		return this.position;
	}

	public String getTitle() {
		return this.title;
	}
}
