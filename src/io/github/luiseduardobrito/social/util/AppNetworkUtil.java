/**
 * 
 */
package io.github.luiseduardobrito.social.util;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.SystemService;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;

@EBean
public class AppNetworkUtil {

	@SystemService
	ConnectivityManager cm;

	public boolean isOnline() {

		boolean status = false;

		try {

			NetworkInfo netInfo = cm.getNetworkInfo(0);

			if (netInfo != null && netInfo.getState() == NetworkInfo.State.CONNECTED) {
				status = true;
			}

			else {

				netInfo = cm.getNetworkInfo(1);

				if (netInfo != null && netInfo.getState() == NetworkInfo.State.CONNECTED)
					status = true;
			}

		} catch (Exception e) {

			e.printStackTrace();
			return false;
		}

		return status;
	}
}
