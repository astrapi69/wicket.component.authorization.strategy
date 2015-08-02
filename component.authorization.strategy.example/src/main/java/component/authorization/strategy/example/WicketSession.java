package component.authorization.strategy.example;

import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.request.Request;

import authorization.strategy.api.Right;


public class WicketSession extends WebSession
{

	private static final long serialVersionUID = 1L;
	private User user;

	public WicketSession(final Request request)
	{
		super(request);
	}

	public User getUser()
	{
		return user;
	}

	public boolean isAuthorized(final Right right)
	{
		return user.isAuthorized(right);
	}

	public boolean login(final String username, final String password)
	{
		if (WicketApplication.getUsernameuser().containsKey(username))
		{
			final User user = WicketApplication.getUsernameuser().get(username);
			if (user.getPassword().equals(password))
			{
				this.user = user;
				return true;
			}
		}
		return false;
	}

	public void setUser(final User user)
	{
		this.user = user;
	}
}