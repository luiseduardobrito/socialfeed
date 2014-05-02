/**
 * 
 */
package io.github.luiseduardobrito.social.model.message;

import io.github.luiseduardobrito.social.model.Message;
import io.github.luiseduardobrito.social.model.MessageState;
import io.github.luiseduardobrito.social.model.MessageType;

/**
 * @author Luis Eduardo Brito
 * 
 */
public class VideoMessage extends Message {

	public VideoMessage(String title) {
		super(title, MessageType.PHOTO);
	}

	public VideoMessage(String title, String timestamp) {
		super(title, MessageType.VIDEO, MessageState.SENT, timestamp);
	}

	public VideoMessage(String title, String timestamp, Integer value) {
		super(title, MessageType.VIDEO, MessageState.SENT, timestamp, value);
	}

}
