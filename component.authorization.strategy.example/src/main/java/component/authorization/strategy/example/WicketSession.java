package component.authorization.strategy.example;
import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.request.Request;

import authorization.strategy.api.Right;


public class WicketSession extends WebSession {

	private static final long serialVersionUID = 1L;
	private User user;

	public WicketSession(Request request) {
		super(request);
	}

	public User getUser() {
		return user;
	}

	public boolean isAuthorized(Right right) {
		return user.isAuthorized(right);
	}

	public boolean login(String username, String password) {
		if(WicketApplication.getUsernameuser().containsKey(username)) {
			User user = WicketApplication.getUsernameuser().get(username);
			if(user.getPassword().equals(password)){
				this.user = user;
				return true;
			}
		}
		return false;
	}

	public void setUser(User user) {
		this.user = user;
	}
}