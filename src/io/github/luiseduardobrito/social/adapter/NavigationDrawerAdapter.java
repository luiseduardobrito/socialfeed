/**
 * 
 */
package io.github.luiseduardobrito.social.adapter;

import io.github.luiseduardobrito.social.fragment.AppFragment;
import io.github.luiseduardobrito.social.view.NavigationDrawerItemView;
import io.github.luiseduardobrito.social.view.NavigationDrawerItemView_;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * @author Luis Eduardo Brito
 * 
 */
public class NavigationDrawerAdapter extends BaseAdapter {

	Context context;

	public NavigationDrawerAdapter(Context context) {
		this.context = context;
	}

	@Override
	public int getCount() {
		return AppFragment.getCount();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getItem(int)
	 */
	@Override
	public Object getItem(int position) {
		return AppFragment.fromPosition(position);
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

		NavigationDrawerItemView view;

		if (convertView == null) {
			view = NavigationDrawerItemView_.build(context);
		}

		else {
			view = (NavigationDrawerItemView) convertView;
		}

		view.bind(AppFragment.fromPosition(position));
		return view;
	}
}
