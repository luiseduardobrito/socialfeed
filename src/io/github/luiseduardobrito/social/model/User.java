/**
 * 
 */
package io.github.luiseduardobrito.social.model;

import io.github.luiseduardobrito.social.exception.AppParseException;

import java.util.List;
import java.util.Observable;

import com.parse.ParseException;
import com.parse.ParseUser;

/**
 * @author Luis Eduardo Brito
 * 
 */
public class User extends Observable {

	private String objectId;
	private String name;
	private String email;

	private List<Message> messages;

	/**
	 * @param mUserObject
	 * @return
	 * @throws ParseException
	 * @throws ParseException
	 */
	protected static User fromParseObject(ParseUser mUserObject) throws ParseException {
		mUserObject = mUserObject.fetchIfNeeded();
		String objectId = mUserObject.getObjectId();
		String name = mUserObject.getString("name");
		String email = mUserObject.getString("email");
		return new User(objectId, name, email);
	}

	/**
	 * @param name
	 * @param email
	 * @param password
	 * @return
	 * @throws com.parse.ParseException
	 */
	public static ParseUser createParseObject(String name, String email, String password)
			throws com.parse.ParseException {
		ParseUser mParseUser = new ParseUser();
		mParseUser.put("name", name);
		mParseUser.setUsername(email);
		mParseUser.setPassword(password);
		mParseUser.setEmail(email);
		mParseUser.signUp();
		return mParseUser;
	}

	/**
	 * @param name
	 * @param email
	 * @param password
	 * @return
	 * @throws AppParseException
	 */
	public static User createAndSave(String name, String email, String password)
			throws AppParseException {
		ParseUser mParseUser;
		try {
			mParseUser = createParseObject(name, email, password);
			return fromParseObject(mParseUser);
		} catch (com.parse.ParseException e) {
			throw AppParseException.fromParse(e);
		}
	}

	/**
	 * @param email
	 * @param password
	 * @return
	 * @throws AppParseException
	 */
	public static User findByCredentials(String email, String password) throws AppParseException {

		try {

			ParseUser mParseUser = ParseUser.logIn(email, password);
			return User.fromParseObject(mParseUser);

		} catch (com.parse.ParseException e) {
			throw AppParseException.fromParse(e);
		}
	}

	public static User getCurrent() throws AppParseException {
		try {
			return fromParseObject(ParseUser.getCurrentUser());
		} catch (ParseException e) {
			e.printStackTrace();
			throw AppParseException.fromParse(e);
		}
	}

	/**
	 * @param objectId
	 * @param name
	 * @param email
	 */
	public User(String objectId, String name, String email) {
		this.objectId = objectId;
		this.name = name;
		this.email = email;
	}

	/**
	 * Set as changed and notify observers
	 */
	private void changeAndNotify() {
		this.setChanged();
		this.notifyObservers();
	}

	/**
	 * @return User objectId in Parse
	 */
	public String getObjectId() {
		return objectId;
	}

	/**
	 * @return User name in Parse
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return User email in Parse
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @return User message list
	 */
	public List<Message> getMessages() {
		return messages;
	}

	/**
	 * Set User name in Parse
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
		changeAndNotify();
	}

	/**
	 * Get the ParseUser object for the instance User
	 * 
	 * @return
	 * @throws AppParseException
	 */
	public ParseUser getParseObject() throws AppParseException {
		try {
			return ParseUser.getQuery().get(this.getObjectId());
		} catch (com.parse.ParseException e) {
			e.printStackTrace();
			throw AppParseException.fromParse(e);
		}
	}

	public List<Message> getInbox() {
		
		return null;
	}
}
