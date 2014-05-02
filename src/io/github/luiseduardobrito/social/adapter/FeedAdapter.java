/**
 * 
 */
package io.github.luiseduardobrito.social.adapter;

import io.github.luiseduardobrito.social.R;
import io.github.luiseduardobrito.social.model.Message;
import io.github.luiseduardobrito.social.model.MessageState;
import io.github.luiseduardobrito.social.model.MessageType;
import io.github.luiseduardobrito.social.model.message.PhotoMessage;
import io.github.luiseduardobrito.social.model.message.VideoMessage;
import io.github.luiseduardobrito.social.view.CardView;
import io.github.luiseduardobrito.social.view.CardView_;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * @author Luis Eduardo Brito
 * 
 */
public class FeedAdapter extends BaseAdapter {

	String[] list;
	Context context;

	public FeedAdapter(Context context) {
		this.context = context;
		list = context.getResources().getStringArray(R.array.feed);
	}

	@Override
	public int getCount() {
		return list.length;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getItem(int)
	 */
	@Override
	public Object getItem(int position) {
		return list[position];
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getItemId(int)
	 */
	@Override
	public long getItemId(int position) {
		return position;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getView(int, android.view.View,
	 * android.view.ViewGroup)
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		Message msg;
		CardView view;

		if (convertView != null) {
			view = (CardView) convertView;
		}

		else {
			view = CardView_.build(context);
		}

		if (position == 0) {
			msg = (Message) new VideoMessage(list[position], "1 hora atrás", 10);

		}

		else if (position == 1) {
			msg = (Message) new PhotoMessage(list[position], "2 horas atrás", 15);
		}

		else {
			msg = new Message(list[position], MessageType.CHECKLIST, MessageState.OPENED,
					"5 horas atrás", 5);
		}

		view.bind(msg);
		return view;
	}
}
