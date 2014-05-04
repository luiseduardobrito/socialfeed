package io.github.luiseduardobrito.social.activity;

import io.github.luiseduardobrito.social.AppPrefs_;
import io.github.luiseduardobrito.social.R;
import io.github.luiseduardobrito.social.exception.AppParseException;
import io.github.luiseduardobrito.social.model.User;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.SystemService;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.sharedpreferences.Pref;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NavUtils;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Activity which displays a login screen to the user, offering registration as
 * well.
 */
@EActivity(R.layout.activity_login)
@OptionsMenu(R.menu.login)
public class LoginActivity extends Activity {

	// Values for email and password at the time of the login attempt.
	private String mEmail;
	private String mPassword;

	// Values for name, email and password at the time of the signup attempt.
	private String mSignupName;
	private String mSignupEmail;
	private String mSignupPassword;

	@Pref
	AppPrefs_ prefs;

	@SystemService
	InputMethodManager imm;

	// Sign in UI references.
	@ViewById(R.id.email)
	EditText mEmailView;

	@ViewById(R.id.password)
	EditText mPasswordView;

	@ViewById(R.id.scroll_form)
	View mScrollFormView;

	@ViewById(R.id.login_status)
	View mLoginStatusView;

	@ViewById(R.id.login_status_message)
	TextView mLoginStatusMessageView;

	@ViewById(R.id.sign_in_button)
	Button mSignInButton;

	// Sign up UI references.
	@ViewById(R.id.signup_name)
	EditText mSignupNameView;

	@ViewById(R.id.signup_email)
	EditText mSignupEmailView;

	@ViewById(R.id.signup_password)
	EditText mSignupPasswordView;

	@ViewById(R.id.signup_button)
	Button mSignupButton;

	@AfterViews()
	void initViews() {

		// Set up the action bar
		setupActionBar();

		// Set up the login form.
		setupLoginForm();

		// Set up the login form.
		setupSignupForm();
	}

	/**
	 * Set up the login form views
	 */
	@UiThread
	void setupLoginForm() {

		String mEmailFromIntent = getIntent().getStringExtra(Intent.EXTRA_EMAIL);
		mEmail = mEmailFromIntent != null && !mEmailFromIntent.isEmpty() ? mEmailFromIntent : null;

		String mEmailFromPrefs = prefs.userEmail().get();
		mEmail = mEmailFromPrefs != null && !mEmailFromPrefs.isEmpty() ? mEmailFromPrefs : null;

		if (mEmail == null) {
			mEmailView.setText("");
		} else {
			mEmailView.setText(mEmail);
		}

		mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
			@Override
			public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
				if (id == R.id.login || id == EditorInfo.IME_NULL) {
					attemptLogin();
					return true;
				}
				return false;
			}
		});

		mSignInButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				attemptLogin();
			}
		});
	}

	/**
	 * Set up the signup form views
	 */
	@UiThread
	void setupSignupForm() {

		mSignupEmail = getIntent().getStringExtra(Intent.EXTRA_EMAIL);
		mSignupEmailView.setText(mSignupEmail);

		mSignupPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
			@Override
			public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
				if (id == R.id.login || id == EditorInfo.IME_NULL) {
					attemptLogin();
					return true;
				}
				return false;
			}
		});

		mSignupButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				attemptSignup();
			}
		});
	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			// Show the Up button in the action bar.
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@OptionsItem(android.R.id.home)
	void actionHome() {
		NavUtils.navigateUpFromSameTask(this);
	}

	/**
	 * Attempts to sign in or register the account specified by the login form.
	 * If there are form errors (invalid email, missing fields, etc.), the
	 * errors are presented and no actual login attempt is made.
	 */
	@UiThread
	void attemptLogin() {

		// Reset errors.
		mEmailView.setError(null);
		mPasswordView.setError(null);

		// Store values at the time of the login attempt.
		mEmail = mEmailView.getText().toString();
		mPassword = mPasswordView.getText().toString();

		boolean cancel = false;
		View focusView = null;

		// Check for a valid password.
		if (TextUtils.isEmpty(mPassword)) {
			mPasswordView.setError(getString(R.string.error_field_required));
			focusView = mPasswordView;
			cancel = true;
		} else if (mPassword.length() < 4) {
			mPasswordView.setError(getString(R.string.error_invalid_password));
			focusView = mPasswordView;
			cancel = true;
		}

		// Check for a valid email address.
		if (TextUtils.isEmpty(mEmail)) {
			mEmailView.setError(getString(R.string.error_field_required));
			focusView = mEmailView;
			cancel = true;
		} else if (!mEmail.contains("@")) {
			mEmailView.setError(getString(R.string.error_invalid_email));
			focusView = mEmailView;
			cancel = true;
		}

		if (cancel) {
			// There was an error; don't attempt login and focus the first
			// form field with an error.
			focusView.requestFocus();
		} else {

			// Hide keyboard
			imm.hideSoftInputFromWindow(mPasswordView.getWindowToken(), 0);

			// Show a progress spinner, and kick off a background task to
			// perform the user login attempt.
			mLoginStatusMessageView.setText(R.string.login_progress_signing_in);
			showProgress(true);
			performLoginBackground(mEmail, mPassword);
		}
	}

	/**
	 * Attempts to sign up the account specified by the signup form.
	 */
	@UiThread
	void attemptSignup() {

		// Reset errors.
		mSignupNameView.setError(null);
		mSignupEmailView.setError(null);
		mSignupPasswordView.setError(null);

		// Store values at the time of the login attempt.
		mSignupName = mSignupNameView.getText().toString();
		mSignupEmail = mSignupEmailView.getText().toString();
		mSignupPassword = mSignupPasswordView.getText().toString();

		boolean cancel = false;
		View focusView = null;

		// Check for a valid name.
		if (TextUtils.isEmpty(mSignupName)) {
			mSignupNameView.setError(getString(R.string.error_field_required));
			focusView = mSignupNameView;
			cancel = true;
		}

		// Check for a valid password.
		if (TextUtils.isEmpty(mSignupPassword)) {
			mSignupPasswordView.setError(getString(R.string.error_field_required));
			focusView = mSignupPasswordView;
			cancel = true;
		} else if (mSignupPassword.length() < 4) {
			mSignupPasswordView.setError(getString(R.string.error_invalid_password));
			focusView = mSignupPasswordView;
			cancel = true;
		}

		// Check for a valid email address.
		if (TextUtils.isEmpty(mSignupEmail)) {
			mSignupEmailView.setError(getString(R.string.error_field_required));
			focusView = mSignupEmailView;
			cancel = true;
		} else if (!mSignupEmail.contains("@")) {
			mSignupEmailView.setError(getString(R.string.error_invalid_email));
			focusView = mSignupEmailView;
			cancel = true;
		}

		if (cancel) {
			// There was an error; don't attempt login and focus the first
			// form field with an error.
			focusView.requestFocus();
		} else {

			// Hide keyboard
			imm.hideSoftInputFromWindow(mSignupPasswordView.getWindowToken(), 0);

			// Show a progress spinner, and kick off a background task to
			// perform the user login attempt.
			mLoginStatusMessageView.setText(R.string.login_progress_signing_up);
			showProgress(true);
			performSignupBackground(mSignupName, mSignupEmail, mSignupPassword);
		}
	}

	@Background
	void performSignupBackground(String name, String email, String password) {
		try {

			User user = User.createAndSave(name, email, password);
			String welcomeMessage = getString(R.string.login_welcome_signuppre);
			welcomeMessage = welcomeMessage.concat(user.getName()).concat(
					getString(R.string.login_welcome_signuppost));
			toastMessage(welcomeMessage);
			notifySuccess(user);

		} catch (AppParseException e) {
			e.printStackTrace();
			toastMessage(e.getMessage());
		}
	}

	@Background
	void performLoginBackground(String email, String password) {

		try {

			User user = User.findByCredentials(email, password);
			String welcomeMessage = getString(R.string.login_welcome_signinpre).concat(" ");
			welcomeMessage = welcomeMessage.concat(user.getName()).concat(
					getString(R.string.login_welcome_signinpost));
			toastMessage(welcomeMessage);
			notifySuccess(user);

		} catch (AppParseException e) {
			e.printStackTrace();
			toastMessage(e.getMessage());
		}
	}

	@UiThread
	void toastMessage(String message) {
		Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
	}

	@UiThread
	void notifySuccess(User user) {
		prefs.userEmail().put(user.getEmail());
		prefs.userName().put(user.getName());
		this.finish();
	}

	/**
	 * Shows the progress UI and hides the login form.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
	private void showProgress(final boolean show) {
		// On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
		// for very easy animations. If available, use these APIs to fade-in
		// the progress spinner.
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
			int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

			mLoginStatusView.setVisibility(View.VISIBLE);
			mLoginStatusView.animate().setDuration(shortAnimTime).alpha(show ? 1 : 0)
					.setListener(new AnimatorListenerAdapter() {
						@Override
						public void onAnimationEnd(Animator animation) {
							mLoginStatusView.setVisibility(show ? View.VISIBLE : View.GONE);
						}
					});

			mScrollFormView.setVisibility(View.VISIBLE);
			mScrollFormView.animate().setDuration(shortAnimTime).alpha(show ? 0 : 1)
					.setListener(new AnimatorListenerAdapter() {
						@Override
						public void onAnimationEnd(Animator animation) {
							mScrollFormView.setVisibility(show ? View.GONE : View.VISIBLE);
						}
					});
		} else {
			// The ViewPropertyAnimator APIs are not available, so simply show
			// and hide the relevant UI components.
			mLoginStatusView.setVisibility(show ? View.VISIBLE : View.GONE);
			mScrollFormView.setVisibility(show ? View.GONE : View.VISIBLE);
		}
	}
}
