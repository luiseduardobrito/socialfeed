/**
 * 
 */
package io.github.luiseduardobrito.social.view;

import io.github.luiseduardobrito.social.R;
import io.github.luiseduardobrito.social.model.Message;
import io.github.luiseduardobrito.social.model.MessageState;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author Luis Eduardo Brito
 * 
 */
@EViewGroup(R.layout.card_feed_item)
public class CardView extends FrameLayout {

	@ViewById(R.id.icon)
	ImageView icon;

	@ViewById(R.id.label)
	TextView label;

	@ViewById(R.id.points)
	TextView points;

	@ViewById(R.id.creator)
	TextView creator;

	public CardView(Context context) {
		super(context);
	}

	public void bind(Message msg) {

		if (msg.getCreator() == null) {
			creator.setVisibility(View.GONE);
		}

		else {
			creator.setVisibility(View.VISIBLE);
			creator.setText(msg.getCreator().getName());
		}

		if (msg.getPoints() == null) {
			points.setVisibility(View.GONE);
		}

		else {

			points.setVisibility(View.VISIBLE);

			MessageState state = msg.getState();
			int resId = state.getResourceId();
			Drawable iconDrawable = getContext().getResources().getDrawable(resId);

			points.setBackground(iconDrawable);
			points.setText(msg.getPoints().toString());
		}

		int resId = msg.getType().getResourceId();
		Drawable iconDrawable = getContext().getResources().getDrawable(resId);
		icon.setImageDrawable(iconDrawable);

		label.setText(msg.getTitle());
	}
}
