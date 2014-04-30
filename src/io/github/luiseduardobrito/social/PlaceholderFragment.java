/**
 * 
 */
package io.github.luiseduardobrito.social;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.widget.TextView;

/**
 * @author Luis Eduardo Brito
 *
 */
/**
 * A placeholder fragment containing a simple view.
 */
@EFragment
public class PlaceholderFragment extends Fragment {

	@ViewById(R.id.section_label)
	TextView textView;

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
		PlaceholderFragment fragment = new PlaceholderFragment();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;
	}

	@AfterViews
	void initViews() {
		textView.setText(Integer.toString(getArguments().getInt(ARG_SECTION_NUMBER)));
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		((MainActivity) activity).onSectionAttached(getArguments().getInt(ARG_SECTION_NUMBER));
	}
}