package io.github.luiseduardobrito.social.activity;

import io.github.luiseduardobrito.social.NavigationDrawerFragment;
import io.github.luiseduardobrito.social.R;
import io.github.luiseduardobrito.social.exception.AppParseException;
import io.github.luiseduardobrito.social.intent.AppIntentActions;
import io.github.luiseduardobrito.social.model.MessageListManager;
import io.github.luiseduardobrito.social.push.AppPushManager;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.UiThread;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.widget.Toast;

import com.parse.ParseAnalytics;

@EActivity(R.layout.activity_main)
@OptionsMenu(R.menu.main)
public class MainActivity extends Activity implements
		NavigationDrawerFragment.NavigationDrawerCallbacks {

	@Bean
	AppPushManager push;

	@Bean
	MessageListManager mMessageList;

	/**
	 * Fragment managing the behaviors, interactions and presentation of the
	 * navigation drawer.
	 */
	private NavigationDrawerFragment mNavigationDrawerFragment;

	/**
	 * Used to store the last screen title. For use in
	 * {@link #restoreActionBar()}.
	 */
	private CharSequence mTitle;

	@AfterInject
	void init() {

		// Prepare push broadcast receiver
		String action = AppIntentActions.MESSAGE_ARRIVED.toString();
		IntentFilter intentFilter = new IntentFilter(action);
		registerReceiver(push.getReceiver(), intentFilter);
	}

	@AfterViews
	void initViews() {

		// Parse analytics
		ParseAnalytics.trackAppOpened(getIntent());

		// Prepare drawer fragment
		mNavigationDrawerFragment = (NavigationDrawerFragment) getFragmentManager()
				.findFragmentById(R.id.navigation_drawer);
		mTitle = getTitle();

		// Set up the drawer.
		mNavigationDrawerFragment.setUp(R.id.navigation_drawer,
				(DrawerLayout) findViewById(R.id.drawer_layout));
	}

	@Override
	public void onNavigationDrawerItemSelected(int position) {

		// update the main content by replacing fragments
		FragmentManager fragmentManager = getFragmentManager();
		fragmentManager.beginTransaction()
				.replace(R.id.container, PlaceholderFragment_.newInstance(position + 1)).commit();
	}

	public void onSectionAttached(int number) {
		switch (number) {
		case 1:
			mTitle = getString(R.string.title_section1);
			break;
		case 2:
			mTitle = getString(R.string.title_section2);
			break;
		case 3:
			mTitle = getString(R.string.title_section3);
			break;
		}
	}

	public void restoreActionBar() {
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle(mTitle);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if (!mNavigationDrawerFragment.isDrawerOpen()) {
			// Only show items in the action bar relevant to this screen
			// if the drawer is not showing. Otherwise, let the drawer
			// decide what to show in the action bar.
			restoreActionBar();
			return true;
		}
		return super.onCreateOptionsMenu(menu);
	}

	@OptionsItem
	void actionAdd() {
		CreatorActivity_.intent(this).startForResult(CreatorActivity.REQUEST_CREATE);
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {

		super.onActivityResult(requestCode, resultCode, data);

		try {
			mMessageList.refresh();
		} catch (AppParseException e) {
			toastError(e.getMessage());
		}
	}

	@UiThread
	void toastError(String message) {
		Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
	}
}
