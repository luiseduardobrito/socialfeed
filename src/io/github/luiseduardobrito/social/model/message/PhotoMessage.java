/**
 * 
 */
package io.github.luiseduardobrito.social.model.message;

import io.github.luiseduardobrito.social.model.Message;
import io.github.luiseduardobrito.social.model.MessageState;
import io.github.luiseduardobrito.social.model.MessageType;
import android.graphics.drawable.Drawable;

/**
 * @author Luis Eduardo Brito
 * 
 */
public class PhotoMessage extends Message {

	private Drawable drawable;

	public PhotoMessage(String title) {
		super(title, MessageType.PHOTO);
	}

	public PhotoMessage(String title, String timestamp) {
		super(title, MessageType.PHOTO, MessageState.SENT, timestamp);
	}

	public PhotoMessage(String title, String timestamp, Integer value) {
		super(title, MessageType.PHOTO, MessageState.SENT, timestamp, value);
	}

	public Drawable getDrawable() {
		return this.drawable;
	}
}
