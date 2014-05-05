/**
 * 
 */
package io.github.luiseduardobrito.social.model;

import io.github.luiseduardobrito.social.exception.AppParseException;
import io.github.luiseduardobrito.social.util.AppNetworkUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.EBean.Scope;

/**
 * @author Luis Eduardo Brito
 * 
 */
@EBean(scope = Scope.Singleton)
public class MessageListManager extends Observable {

	List<Message> feed;
	List<Message> sent;

	@Bean
	AppNetworkUtil network;

	@AfterInject
	void init() {

		try {

			this.refresh();

		} catch (AppParseException e) {
			throw new RuntimeException(e);
		}
	}

	public void refresh() throws AppParseException {

		Boolean isOnline = network.isOnline();

		if (isOnline) {
			this.feed = Message.getUserFeed(User.getCurrent());
			this.sent = Message.findByCreator(User.getCurrent());
		}

		else {
			
			// TODO: handle offline requests			
			this.feed = new ArrayList<Message>();
			this.sent = new ArrayList<Message>();
		}

		this.setChanged();
		this.notifyObservers();
	}

	public List<Message> getFeed() {
		return this.feed;
	}

	public List<Message> getSent() {
		return this.sent;
	}
}
