/**
 * 
 */
package io.github.luiseduardobrito.social.model;

import io.github.luiseduardobrito.social.exception.AppParseException;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

/**
 * @author Luis Eduardo Brito
 * 
 */
public class Message extends Observable {

	private String title;
	private Integer points;

	private MessageType type;
	private MessageState state;

	private User creator;

	/**
	 * @param mMessageObject
	 * @return
	 * @throws ParseException
	 */
	protected static Message fromParseObject(ParseObject mMessageObject) throws ParseException {

		String title = mMessageObject.getString("title");
		User creator = User.fromParseObject(mMessageObject.getParseUser("creator"));
		MessageType type = MessageType.fromString(mMessageObject.getString("type"));

		Message message = new Message(title, type, creator);
		message.setState(MessageState.fromString(mMessageObject.getString("state")));
		message.setPoints(mMessageObject.getNumber("points").intValue());

		return message;
	}

	/**
	 * @param message
	 * @return
	 * @throws ParseException
	 */
	protected static ParseObject createParseObject(Message message) throws ParseException {
		ParseObject mMessageObject = new ParseObject("Message");
		mMessageObject.put("title", message.getTitle());
		mMessageObject.put("type", message.getType().toString());
		mMessageObject.put("state", message.getState().toString());
		mMessageObject.put("points", message.getPoints());
		mMessageObject.put("creator", message.getCreator().getParseObject());
		return mMessageObject;
	}

	/**
	 * @param message
	 * @return
	 * @throws AppParseException
	 */
	protected static ParseObject saveParseObject(Message message) throws AppParseException {

		try {

			ParseObject mMessageObject = createParseObject(message);
			mMessageObject.save();
			return mMessageObject;

		} catch (ParseException e) {
			throw AppParseException.fromParse(e);
		}
	}

	/**
	 * @return
	 * @throws AppParseException
	 */
	protected static List<ParseObject> findParseObject() throws AppParseException {

		ParseQuery<ParseObject> query = ParseQuery.getQuery("Message");

		try {
			return query.find();
		} catch (ParseException e) {
			throw AppParseException.fromParse(e);
		}
	}

	/**
	 * @param objectId
	 * @return
	 * @throws AppParseException
	 */
	protected static ParseObject findByIdParseObjects(String objectId) throws AppParseException {

		ParseQuery<ParseObject> query = ParseQuery.getQuery("Message");
		try {
			return query.get(objectId);
		} catch (ParseException e) {
			throw AppParseException.fromParse(e);
		}
	}

	/**
	 * Get full message list
	 * 
	 * @return
	 * @throws AppParseException
	 */
	public static List<Message> find() throws AppParseException {

		List<Message> mMessageList = new ArrayList<Message>();

		try {

			for (ParseObject p : findParseObject()) {
				mMessageList.add(Message.fromParseObject(p));
			}

		} catch (ParseException e) {
			throw AppParseException.fromParse(e);
		}

		return mMessageList;
	}

	/**
	 * Get single message by objectId
	 * 
	 * @param objectId
	 * @return
	 * @throws AppParseException
	 */
	public static Message findById(String objectId) throws AppParseException {
		try {
			return Message.fromParseObject(findByIdParseObjects(objectId));
		} catch (ParseException e) {
			e.printStackTrace();
			throw AppParseException.fromParse(e);
		}
	}

	/**
	 * Find messages by creator instance
	 * 
	 * @param creator
	 * @return
	 * @throws AppParseException
	 */
	public static List<Message> findByCreator(User creator) throws AppParseException {
		return findByCreator(creator.getParseObject());
	}

	/**
	 * Find messages by creator id
	 * 
	 * @param creatorId
	 * @return
	 * @throws AppParseException
	 */
	public static List<Message> findByCreator(ParseUser mParseUser) throws AppParseException {

		List<Message> result = new ArrayList<Message>();

		try {

			ParseQuery<ParseObject> query = ParseQuery.getQuery("Message");
			query.whereEqualTo("creator", mParseUser);

			for (ParseObject mParseObject : query.find()) {
				result.add(Message.fromParseObject(mParseObject));
			}

			return result;

		} catch (ParseException e) {
			e.printStackTrace();
			throw AppParseException.fromParse(e);
		}
	}

	/**
	 * Get messages not created by user instance
	 * 
	 * @param user
	 * @return
	 * @throws AppParseException
	 */
	public static List<Message> getUserFeed(User user) throws AppParseException {
		return getUserFeed(user.getParseObject());
	}

	/**
	 * Get messages not created by user referenced by mParseUser
	 * 
	 * @param mParseUser
	 * @return
	 * @throws AppParseException
	 */
	public static List<Message> getUserFeed(ParseUser mParseUser) throws AppParseException {

		List<Message> result = new ArrayList<Message>();

		try {

			ParseQuery<ParseObject> query = ParseQuery.getQuery("Message");
			query.whereNotEqualTo("creator", mParseUser);

			for (ParseObject mParseObject : query.find()) {
				result.add(Message.fromParseObject(mParseObject));
			}

			return result;

		} catch (ParseException e) {
			e.printStackTrace();
			throw AppParseException.fromParse(e);
		}
	}

	/**
	 * Create a new message and save it in Parse
	 * 
	 * @param title
	 * @param type
	 * @param creator
	 * @param points
	 * @return
	 * @throws AppParseException
	 */
	public static Message createAndSave(String title, MessageType type, User creator, Integer points)
			throws AppParseException {
		Message message = new Message(title, type, creator);
		message.setPoints(points);
		saveParseObject(message);
		return message;
	}

	/**
	 * Create a new message and save it in Parse
	 * 
	 * @param title
	 * @param type
	 * @param creator
	 * @return
	 * @throws AppParseException
	 */
	public static Message createAndSave(String title, MessageType type, User creator)
			throws AppParseException {
		return createAndSave(title, type, creator, 0);
	}

	/**
	 * Create a new message
	 * 
	 * @param title
	 * @param type
	 * @param creator
	 */
	public Message(String title, MessageType type, User creator) {
		this.title = title;
		this.type = type;
		this.creator = creator;
		this.state = MessageState.SENT;
		this.points = 0;
	}

	/**
	 * Create a new message
	 * 
	 * @param title
	 * @param type
	 * @param creator
	 * @param state
	 */
	public Message(String title, MessageType type, User creator, MessageState state) {
		this.title = title;
		this.type = type;
		this.creator = creator;
		this.state = state;
		this.points = 0;
	}

	/**
	 * Create a new message
	 * 
	 * @param title
	 * @param type
	 * @param creator
	 * @param state
	 * @param points
	 */
	public Message(String title, MessageType type, User creator, MessageState state, Integer points) {
		this.title = title;
		this.type = type;
		this.creator = creator;
		this.state = state;
		this.points = points;
	}

	/**
	 * Set observable instance as changed and notify observers
	 */
	private void changeAndNotify() {
		this.setChanged();
		this.notifyObservers();
	}

	/**
	 * @return Message state
	 */
	public MessageState getState() {
		return state;
	}

	/**
	 * Set message state
	 * 
	 * @param state
	 */
	public void setState(MessageState state) {
		this.state = state;
		changeAndNotify();
	}

	/**
	 * @return Message title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Set message title
	 * 
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
		changeAndNotify();
	}

	/**
	 * @return Message type
	 */
	public MessageType getType() {
		return type;
	}

	/**
	 * Set message type
	 * 
	 * @param type
	 */
	public void setType(MessageType type) {
		this.type = type;
		changeAndNotify();
	}

	/**
	 * Get message reward points
	 * 
	 * @return
	 */
	public Integer getPoints() {
		return points;
	}

	/**
	 * Set message reward points and notify observers
	 * 
	 * @param points
	 */
	public void setPoints(Integer points) {
		this.points = points;
		changeAndNotify();
	}

	/**
	 * @return Message creator instance
	 */
	public User getCreator() {
		return this.creator;
	}
}
