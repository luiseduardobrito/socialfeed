package io.github.luiseduardobrito.social;

import io.github.luiseduardobrito.social.activity.MainActivity_;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EApplication;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseInstallation;
import com.parse.PushService;

/**
 * 
 */

/**
 * @author Luis Eduardo Brito
 * 
 */
@EApplication
public class App extends Application {

	@AfterInject
	void init() {

		Parse.initialize(this, "Rwwzb2fmU0dIY6nx1A4q7DEZB9zlrwaN1RyS8lVK",
				"wW60eAge3yrD5qk03bS6RSFmQSzWuUjckyXk8qGL");

		Parse.enableLocalDatastore(this);
		ParseInstallation.getCurrentInstallation().saveInBackground();
		PushService.subscribe(this, "message", MainActivity_.class);
		PushService.setDefaultPushCallback(this, MainActivity_.class);
	}
}
