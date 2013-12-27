package component.authorization.strategy.example;
import java.util.List;

import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.request.Request;

import authorization.strategy.api.Right;


public class WicketSession extends WebSession {

	private static final long serialVersionUID = 1L;
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public WicketSession(Request request) {
		super(request);
	}

	public boolean login(String username, String password) {
		List<User> users = WicketApplication.getUsers();
		for (User user : users) {			
			if(user.getUsername().equals(username) && user.getPassword().equals(password)){
				this.user = user;
				return true;
			}
		}
		return false;
	}

	public boolean isAuthorized(Right right) {
		return user.isAuthorized(right);
	}
}