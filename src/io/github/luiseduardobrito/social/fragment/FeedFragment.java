/**
 * 
 */
package io.github.luiseduardobrito.social.fragment;

import io.github.luiseduardobrito.social.R;
import io.github.luiseduardobrito.social.activity.MainActivity;
import io.github.luiseduardobrito.social.adapter.FeedAdapter;
import io.github.luiseduardobrito.social.exception.AppParseException;
import io.github.luiseduardobrito.social.model.MessageListManager;

import java.util.Observable;
import java.util.Observer;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

/**
 * @author Luis Eduardo Brito
 *
 */
/**
 * A placeholder fragment containing a simple view.
 */
@EFragment(R.layout.fragment_main)
public class FeedFragment extends Fragment implements Observer {

	FeedAdapter adapter;

	@Bean
	MessageListManager mMessageList;

	@ViewById(R.id.feed)
	ListView feed;

	/**
	 * The fragment argument representing the section number for this
	 * fragment.
	 */
	private static final String ARG_SECTION_NUMBER = "section_number";

	/**
	 * Returns a new instance of this fragment for the given section
	 * number.
	 */
	public static FeedFragment newInstance(int sectionNumber) {
		FeedFragment fragment = FeedFragment_.builder().build();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;
	}

	@AfterInject
	void init() {
		mMessageList.addObserver(this);
	}

	@AfterViews
	void initViews() {

		try {
			adapter = new FeedAdapter(getActivity(), mMessageList.getFeed());
		} catch (AppParseException e) {
			toastError(e.getMessage());
		}

		feed.setAdapter(adapter);
	}

	@UiThread
	void toastError(String message) {
		Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		((MainActivity) activity).onSectionAttached(getArguments().getInt(ARG_SECTION_NUMBER));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable observable, Object data) {
		refreshAdapter();
	}

	@UiThread
	void refreshAdapter() {
		try {
			adapter.refresh(mMessageList.getFeed());
		} catch (AppParseException e) {
			toastError(e.getMessage());
		}
	}
}