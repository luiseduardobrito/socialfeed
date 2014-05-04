/**
 * 
 */
package io.github.luiseduardobrito.social.adapter;

import io.github.luiseduardobrito.social.exception.AppParseException;
import io.github.luiseduardobrito.social.model.Message;
import io.github.luiseduardobrito.social.view.CardView;
import io.github.luiseduardobrito.social.view.CardView_;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * @author Luis Eduardo Brito
 * 
 */
public class FeedAdapter extends BaseAdapter {

	List<Message> list;
	Context context;

	public FeedAdapter(Context context, List<Message> list) throws AppParseException {
		this.context = context;
		this.refresh(list);
	}
	
	public void refresh(List<Message> list) throws AppParseException {
		this.list = list;
		this.notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return list.size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getItem(int)
	 */
	@Override
	public Object getItem(int position) {
		return list.get(position);
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

		CardView view;

		if (convertView != null) {
			view = (CardView) convertView;
		}

		else {
			view = CardView_.build(context);
		}

		view.bind(list.get(position));
		return view;
	}
}
