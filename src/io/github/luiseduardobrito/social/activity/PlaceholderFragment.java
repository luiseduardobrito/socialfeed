/**
 * 
 */
package io.github.luiseduardobrito.social.activity;

import io.github.luiseduardobrito.social.R;
import io.github.luiseduardobrito.social.adapter.FeedAdapter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.widget.ListView;

/**
 * @author Luis Eduardo Brito
 *
 */
/**
 * A placeholder fragment containing a simple view.
 */
@EFragment(R.layout.fragment_main)
public class PlaceholderFragment extends Fragment {

	FeedAdapter adapter;

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
	public static PlaceholderFragment newInstance(int sectionNumber) {
		PlaceholderFragment fragment = PlaceholderFragment_.builder().build();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;
	}

	@AfterViews
	void initViews() {
		adapter = new FeedAdapter(getActivity());
		feed.setAdapter(adapter);
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		((MainActivity) activity).onSectionAttached(getArguments().getInt(ARG_SECTION_NUMBER));
	}
}