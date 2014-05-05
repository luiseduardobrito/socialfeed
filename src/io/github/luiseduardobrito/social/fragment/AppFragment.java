/**
 * 
 */
package io.github.luiseduardobrito.social.fragment;

import io.github.luiseduardobrito.social.R;
import android.app.Fragment;

/**
 * @author Luis Eduardo Brito
 * 
 */
public enum AppFragment {

	FEED(0, "Feed", R.drawable.reorder), SENT(1, "Sent", R.drawable.comment33), SETTINGS(
			2,
			"Settings",
			android.R.drawable.ic_menu_preferences);

	public static Integer getCount() {
		return AppFragment.values().length;
	}

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

		if (fragmentEnum.equals(AppFragment.FEED)) {
			return FeedFragment_.newInstance(position);
		}

		if (fragmentEnum.equals(AppFragment.SENT)) {
			return SentFragment_.newInstance(position);
		}

		return null;
	}

	private String title;
	private Integer position;
	private int iconResourceId;

	private AppFragment(Integer position, String title, int iconResourceId) {
		this.position = position;
		this.title = title;
		this.iconResourceId = iconResourceId;
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

	public int getIconResourceId() {
		return iconResourceId;
	}
}
