/**
 * 
 */
package io.github.luiseduardobrito.social.model.message;

import io.github.luiseduardobrito.social.model.Message;
import io.github.luiseduardobrito.social.model.MessageState;
import io.github.luiseduardobrito.social.model.MessageType;
import io.github.luiseduardobrito.social.model.User;

/**
 * @author Luis Eduardo Brito
 * 
 */
public class VideoMessage extends Message {

	public VideoMessage(String title, User creator) {
		super(title, MessageType.PHOTO, creator);
	}

	public VideoMessage(String title, User creator, Integer value) {
		super(title, MessageType.VIDEO, creator, MessageState.SENT, value);
	}
}
