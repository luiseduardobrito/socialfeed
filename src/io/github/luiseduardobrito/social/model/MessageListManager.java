/**
 * 
 */
package io.github.luiseduardobrito.social.model;

import io.github.luiseduardobrito.social.exception.AppParseException;

import java.util.List;
import java.util.Observable;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.EBean.Scope;

/**
 * @author Luis Eduardo Brito
 * 
 */
@EBean(scope = Scope.Singleton)
public class MessageListManager extends Observable {

	List<Message> list;

	@AfterInject
	void init() {

		try {

			this.refresh();

		} catch (AppParseException e) {
			throw new RuntimeException(e);
		}
	}

	public void refresh() throws AppParseException {
		this.list = Message.find();
		this.setChanged();
		this.notifyObservers();
	}

	public List<Message> get() {
		return this.list;
	}
}
