/**
 * 
 */
package io.github.luiseduardobrito.social;

import org.androidannotations.annotations.sharedpreferences.SharedPref;

/**
 * @author Luis Eduardo Brito
 * 
 */
@SharedPref
public interface AppPrefs {

	String userId();

	String userName();

	String userEmail();
}