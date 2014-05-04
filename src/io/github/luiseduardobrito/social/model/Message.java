/**
 * 
 */
package io.github.luiseduardobrito.social.model;

import java.util.List;
import java.util.Observable;

import com.parse.ParseException;
import com.parse.ParseObject;

/**
 * @author Luis Eduardo Brito
 * 
 */
public class Message extends Observable {

	private String title;
	private String timestamp;
	private Integer points;

	private MessageType type;
	private MessageState state;

	private List<Message> answers;

	public static ParseObject saveParseObject(Message message) throws ParseException {
		ParseObject mMessageObject = new ParseObject("Message");
		mMessageObject.put("title", message.getTitle());
		mMessageObject.put("type", message.getType());
		mMessageObject.put("state", message.getState());
		mMessageObject.save();
		return mMessageObject;
	}

	public static Message createAndSave(String title, MessageType type) throws ParseException {
		Message message = new Message(title, type);
		saveParseObject(message);
		return message;
	}

	public Message(String title, MessageType type) {
		this.title = title;
		this.type = type;
		this.state = MessageState.SENT;
	}

	public Message(String title, MessageType type, MessageState state) {
		this.title = title;
		this.type = type;
		this.state = state;
	}

	public Message(String title, MessageType type, MessageState state, String timestamp) {
		this.title = title;
		this.type = type;
		this.state = state;
		this.timestamp = timestamp;
	}

	public Message(String title, MessageType type, MessageState state, String timestamp,
			Integer points) {
		this.title = title;
		this.type = type;
		this.state = state;
		this.timestamp = timestamp;
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

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
		changeAndNotify();
	}

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
		changeAndNotify();
	}

	public List<Message> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Message> answers) {
		this.answers = answers;
		changeAndNotify();
	}
}
