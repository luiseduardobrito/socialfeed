/**
 * 
 */
package io.github.luiseduardobrito.social.push;

import io.github.luiseduardobrito.social.activity.MainActivity_;
import io.github.luiseduardobrito.social.intent.AppIntentActions;

import java.util.Iterator;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class AppPushReceiver extends BroadcastReceiver {

	private static final String TAG = AppPushReceiver.class.getName();

	public static AppPushReceiver instance = null;

	public static AppPushReceiver getInstance() {

		if (instance == null) {
			instance = new AppPushReceiver();
		}

		return instance;
	}

	public AppPushReceiver() {
		instance = this;
	}

	@Override
	public void onReceive(Context context, Intent intent) {

		try {

			if (intent == null) {
				Log.d(TAG, "Receiver intent null");
			}

			else {

				String action = intent.getAction();
				Log.d(TAG, "got action " + action);

				if (action.equals(AppIntentActions.MESSAGE_ARRIVED.toString())) {

					String channel = intent.getExtras().getString("com.parse.Channel");
					JSONObject json = new JSONObject(intent.getExtras().getString("com.parse.Data"));

					Log.d(TAG, "got action " + action + " on channel " + channel + " with:");
					Iterator<?> itr = json.keys();

					while (itr.hasNext()) {

						String key = (String) itr.next();

						if (key.equals("customdata")) {
							Intent pupInt = new Intent(context, MainActivity_.class);
							pupInt.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
							context.getApplicationContext().startActivity(pupInt);
						}

						Log.d(TAG, "..." + key + " => " + json.getString(key));
					}
				}
			}

		} catch (JSONException e) {
			Log.d(TAG, "JSONException: " + e.getMessage());
		}
	}
}
