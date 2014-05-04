//
// DO NOT EDIT THIS FILE, IT HAS BEEN GENERATED USING AndroidAnnotations 3.0.1.
//


package io.github.luiseduardobrito.social.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import io.github.luiseduardobrito.social.AppPrefs_;
import io.github.luiseduardobrito.social.R.layout;
import io.github.luiseduardobrito.social.model.User;
import org.androidannotations.api.BackgroundExecutor;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class LoginActivity_
    extends LoginActivity
    implements HasViews, OnViewChangedListener
{

    private final OnViewChangedNotifier onViewChangedNotifier_ = new OnViewChangedNotifier();
    private Handler handler_ = new Handler(Looper.getMainLooper());

    @Override
    public void onCreate(Bundle savedInstanceState) {
        OnViewChangedNotifier previousNotifier = OnViewChangedNotifier.replaceNotifier(onViewChangedNotifier_);
        init_(savedInstanceState);
        super.onCreate(savedInstanceState);
        OnViewChangedNotifier.replaceNotifier(previousNotifier);
        setContentView(layout.activity_login);
    }

    private void init_(Bundle savedInstanceState) {
        prefs = new AppPrefs_(this);
        OnViewChangedNotifier.registerOnViewChangedListener(this);
        imm = ((InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE));
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        onViewChangedNotifier_.notifyViewChanged(this);
    }

    @Override
    public void setContentView(View view, LayoutParams params) {
        super.setContentView(view, params);
        onViewChangedNotifier_.notifyViewChanged(this);
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        onViewChangedNotifier_.notifyViewChanged(this);
    }

    public static LoginActivity_.IntentBuilder_ intent(Context context) {
        return new LoginActivity_.IntentBuilder_(context);
    }

    public static LoginActivity_.IntentBuilder_ intent(android.app.Fragment fragment) {
        return new LoginActivity_.IntentBuilder_(fragment);
    }

    public static LoginActivity_.IntentBuilder_ intent(android.support.v4.app.Fragment supportFragment) {
        return new LoginActivity_.IntentBuilder_(supportFragment);
    }

    @Override
    public void onViewChanged(HasViews hasViews) {
        mScrollFormView = ((View) hasViews.findViewById(io.github.luiseduardobrito.social.R.id.scroll_form));
        mLoginStatusMessageView = ((TextView) hasViews.findViewById(io.github.luiseduardobrito.social.R.id.login_status_message));
        mSignupPasswordView = ((EditText) hasViews.findViewById(io.github.luiseduardobrito.social.R.id.signup_password));
        mSignInButton = ((Button) hasViews.findViewById(io.github.luiseduardobrito.social.R.id.sign_in_button));
        mSignupNameView = ((EditText) hasViews.findViewById(io.github.luiseduardobrito.social.R.id.signup_name));
        mSignupButton = ((Button) hasViews.findViewById(io.github.luiseduardobrito.social.R.id.signup_button));
        mLoginStatusView = ((View) hasViews.findViewById(io.github.luiseduardobrito.social.R.id.login_status));
        mPasswordView = ((EditText) hasViews.findViewById(io.github.luiseduardobrito.social.R.id.password));
        mSignupEmailView = ((EditText) hasViews.findViewById(io.github.luiseduardobrito.social.R.id.signup_email));
        mEmailView = ((EditText) hasViews.findViewById(io.github.luiseduardobrito.social.R.id.email));
        initViews();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(io.github.luiseduardobrito.social.R.menu.login, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean handled = super.onOptionsItemSelected(item);
        if (handled) {
            return true;
        }
        int itemId_ = item.getItemId();
        if (itemId_ == android.R.id.home) {
            actionHome();
            return true;
        }
        return false;
    }

    @Override
    public void attemptSignup() {
        handler_.post(new Runnable() {


            @Override
            public void run() {
                LoginActivity_.super.attemptSignup();
            }

        }
        );
    }

    @Override
    public void attemptLogin() {
        handler_.post(new Runnable() {


            @Override
            public void run() {
                LoginActivity_.super.attemptLogin();
            }

        }
        );
    }

    @Override
    public void setupSignupForm() {
        handler_.post(new Runnable() {


            @Override
            public void run() {
                LoginActivity_.super.setupSignupForm();
            }

        }
        );
    }

    @Override
    public void toastMessage(final String message) {
        handler_.post(new Runnable() {


            @Override
            public void run() {
                LoginActivity_.super.toastMessage(message);
            }

        }
        );
    }

    @Override
    public void notifySuccess(final User user) {
        handler_.post(new Runnable() {


            @Override
            public void run() {
                LoginActivity_.super.notifySuccess(user);
            }

        }
        );
    }

    @Override
    public void setupLoginForm() {
        handler_.post(new Runnable() {


            @Override
            public void run() {
                LoginActivity_.super.setupLoginForm();
            }

        }
        );
    }

    @Override
    public void performSignupBackground(final String name, final String email, final String password) {
        BackgroundExecutor.execute(new BackgroundExecutor.Task("", 0, "") {


            @Override
            public void execute() {
                try {
                    LoginActivity_.super.performSignupBackground(name, email, password);
                } catch (Throwable e) {
                    Thread.getDefaultUncaughtExceptionHandler().uncaughtException(Thread.currentThread(), e);
                }
            }

        }
        );
    }

    @Override
    public void performLoginBackground(final String email, final String password) {
        BackgroundExecutor.execute(new BackgroundExecutor.Task("", 0, "") {


            @Override
            public void execute() {
                try {
                    LoginActivity_.super.performLoginBackground(email, password);
                } catch (Throwable e) {
                    Thread.getDefaultUncaughtExceptionHandler().uncaughtException(Thread.currentThread(), e);
                }
            }

        }
        );
    }

    public static class IntentBuilder_ {

        private Context context_;
        private final Intent intent_;
        private android.app.Fragment fragment_;
        private android.support.v4.app.Fragment fragmentSupport_;

        public IntentBuilder_(Context context) {
            context_ = context;
            intent_ = new Intent(context, LoginActivity_.class);
        }

        public IntentBuilder_(android.app.Fragment fragment) {
            fragment_ = fragment;
            context_ = fragment.getActivity();
            intent_ = new Intent(context_, LoginActivity_.class);
        }

        public IntentBuilder_(android.support.v4.app.Fragment fragment) {
            fragmentSupport_ = fragment;
            context_ = fragment.getActivity();
            intent_ = new Intent(context_, LoginActivity_.class);
        }

        public Intent get() {
            return intent_;
        }

        public LoginActivity_.IntentBuilder_ flags(int flags) {
            intent_.setFlags(flags);
            return this;
        }

        public void start() {
            context_.startActivity(intent_);
        }

        public void startForResult(int requestCode) {
            if (fragmentSupport_!= null) {
                fragmentSupport_.startActivityForResult(intent_, requestCode);
            } else {
                if (fragment_!= null) {
                    fragment_.startActivityForResult(intent_, requestCode);
                } else {
                    if (context_ instanceof Activity) {
                        ((Activity) context_).startActivityForResult(intent_, requestCode);
                    } else {
                        context_.startActivity(intent_);
                    }
                }
            }
        }

    }

}
