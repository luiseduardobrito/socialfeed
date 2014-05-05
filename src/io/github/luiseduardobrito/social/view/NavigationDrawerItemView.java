/**
 * 
 */
package io.github.luiseduardobrito.social.view;

import io.github.luiseduardobrito.social.R;
import io.github.luiseduardobrito.social.fragment.AppFragment;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * @author Luis Eduardo Brito
 * 
 */
@EViewGroup(R.layout.list_drawer_item)
public class NavigationDrawerItemView extends RelativeLayout {

	@ViewById(R.id.icon)
	ImageView iconView;

	@ViewById(R.id.label)
	TextView labelView;

	/**
	 * @param context
	 */
	public NavigationDrawerItemView(Context context) {
		super(context);
	}

	public void bind(AppFragment fragment) {
		bind(fragment.getTitle(), fragment.getIconResourceId());
	}

	protected void bind(String title) {
		labelView.setText(title);
		iconView.setVisibility(View.INVISIBLE);
	}

	protected void bind(String title, int drawableResourceId) {

		labelView.setText(title);

		iconView.setVisibility(View.VISIBLE);
		iconView.setImageResource(drawableResourceId);
	}
}
