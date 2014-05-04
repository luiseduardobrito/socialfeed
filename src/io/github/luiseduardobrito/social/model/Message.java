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

	protected static Message fromParseObject(ParseObject mMessageObject) throws ParseException {

		String title = mMessageObject.getString("title");
		User creator = User.fromParseObject(mMessageObject.getParseUser("creator"));
		MessageType type = MessageType.fromString(mMessageObject.getString("type"));

		Message message = new Message(title, type, creator);
		message.setState(MessageState.fromString(mMessageObject.getString("state")));
		message.setPoints(mMessageObject.getNumber("points").intValue());

		return message;
	}

	protected static ParseObject createParseObject(Message message) throws ParseException {
		ParseObject mMessageObject = new ParseObject("Message");
		mMessageObject.put("title", message.getTitle());
		mMessageObject.put("type", message.getType().toString());
		mMessageObject.put("state", message.getState().toString());
		mMessageObject.put("points", message.getPoints());
		mMessageObject.put("creator", message.getCreator().getParseObject());
		return mMessageObject;
	}

	protected static ParseObject saveParseObject(Message message) throws AppParseException {

		try {

			ParseObject mMessageObject = createParseObject(message);
			mMessageObject.save();
			return mMessageObject;

		} catch (ParseException e) {
			throw AppParseException.fromParse(e);
		}
	}

	protected static List<ParseObject> findParseObjects() throws AppParseException {

		ParseQuery<ParseObject> query = ParseQuery.getQuery("Message");

		try {
			return query.find();
		} catch (ParseException e) {
			throw AppParseException.fromParse(e);
		}
	}

	public static List<Message> find() throws AppParseException {

		List<Message> mMessageList = new ArrayList<Message>();

		try {

			for (ParseObject p : findParseObjects()) {
				mMessageList.add(Message.fromParseObject(p));
			}

		} catch (ParseException e) {
			throw AppParseException.fromParse(e);
		}

		return mMessageList;
	}

	public static Message createAndSave(String title, MessageType type, User creator, Integer points)
			throws AppParseException {
		Message message = new Message(title, type, creator);
		message.setPoints(points);
		saveParseObject(message);
		return message;
	}

	public static Message createAndSave(String title, MessageType type, User creator)
			throws AppParseException {
		return createAndSave(title, type, creator, 0);
	}

	public Message(String title, MessageType type, User creator) {
		this.title = title;
		this.type = type;
		this.creator = creator;
		this.state = MessageState.SENT;
		this.points = 0;
	}

	public Message(String title, MessageType type, User creator, MessageState state) {
		this.title = title;
		this.type = type;
		this.creator = creator;
		this.state = state;
		this.points = 0;
	}

	public Message(String title, MessageType type, User creator, MessageState state, Integer points) {
		this.title = title;
		this.type = type;
		this.creator = creator;
		this.state = state;
		this.points = points;
	}

	private void changeAndNotify() {
		this.setChanged();
		this.notifyObservers();
	}

	public MessageState getState() {
		return state;
	}

	public void setState(MessageState state) {
		this.state = state;
		changeAndNotify();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
		changeAndNotify();
	}

	public MessageType getType() {
		return type;
	}

	public void setType(MessageType type) {
		this.type = type;
		changeAndNotify();
	}

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
		changeAndNotify();
	}

	public User getCreator() {
		return this.creator;
	}
}
