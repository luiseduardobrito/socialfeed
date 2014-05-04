/**
 * 
 */
package io.github.luiseduardobrito.social.model;

import java.util.List;
import java.util.Observable;

/**
 * @author Luis Eduardo Brito
 * 
 */
public class User extends Observable {

	private Integer id;
	private String name;
	private String email;
	private String password;

	private List<Message> messages;

	public User(String name, String email, String password) {
		this.name = name;
		this.email = email;
		this.password = password;
	}

	private void changeAndNotify() {
		this.setChanged();
		this.notifyObservers();
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		changeAndNotify();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
		changeAndNotify();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
		changeAndNotify();
	}

	public List<Message> getMessages() {
		return messages;
	}
}
