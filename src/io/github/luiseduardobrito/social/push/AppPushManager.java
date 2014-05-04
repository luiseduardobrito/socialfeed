/**
 * 
 */
package io.github.luiseduardobrito.social.push;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.EBean.Scope;

/**
 * @author Luis Eduardo Brito
 * 
 */
@EBean(scope = Scope.Singleton)
public class AppPushManager {

	public AppPushReceiver getReceiver() {
		return AppPushReceiver.getInstance();
	}
}
