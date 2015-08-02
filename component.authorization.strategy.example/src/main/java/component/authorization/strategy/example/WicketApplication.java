package component.authorization.strategy.example;

import java.util.HashMap;
import java.util.Map;

import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;


public class WicketApplication extends WebApplication
{
	public static final int DEFAULT_HTTP_PORT = 9090;
	public static final int DEFAULT_HTTPS_PORT = 9443;

	private static final Map<String, User> usernameUser = new HashMap<>();

	static
	{
		final User barman = new User("barman");
		barman.setPassword("secret");
		barman.add(ApplicationRight.VIEW_NAME);
		barman.add(ApplicationRight.EDIT_NAME);
		barman.add(ApplicationRight.VIEW_DESCRIPTION);
		usernameUser.put(barman.getUsername(), barman);
		final User waiter = new User("waiter");
		waiter.setPassword("secret");
		waiter.add(ApplicationRight.VIEW_NAME);
		usernameUser.put(waiter.getUsername(), waiter);
		final User boss = new User("boss");
		boss.setPassword("secret");
		boss.add(ApplicationRight.VIEW_NAME);
		boss.add(ApplicationRight.VIEW_DESCRIPTION);
		boss.add(ApplicationRight.EDIT_NAME);
		boss.add(ApplicationRight.EDIT_DESCRIPTION);
		usernameUser.put(boss.getUsername(), boss);
	}

	public static Map<String, User> getUsernameuser()
	{
		return usernameUser;
	}

	@Override
	public Class<HomePage> getHomePage()
	{
		return HomePage.class;
	}

	@Override
	protected void init()
	{
		getSecuritySettings().setAuthorizationStrategy(new ApplicationAuthorizationStrategy());
	}

	@Override
	public Session newSession(final Request request, final Response response)
	{
		return new WicketSession(request);
	}
}