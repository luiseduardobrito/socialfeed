/**
 * 
 */
package io.github.luiseduardobrito.social.model.message;

import io.github.luiseduardobrito.social.model.Message;
import io.github.luiseduardobrito.social.model.MessageState;
import io.github.luiseduardobrito.social.model.MessageType;
import io.github.luiseduardobrito.social.model.User;
import android.graphics.drawable.Drawable;

/**
 * @author Luis Eduardo Brito
 * 
 */
public class PhotoMessage extends Message {

	private Drawable drawable;

	public PhotoMessage(String title, User creator) {
		super(title, MessageType.PHOTO, creator, MessageState.SENT);
	}

	public PhotoMessage(String title, User creator, Integer value) {
		super(title, MessageType.PHOTO, creator, MessageState.SENT, value);
	}

	public Drawable getDrawable() {
		return this.drawable;
	}
}
