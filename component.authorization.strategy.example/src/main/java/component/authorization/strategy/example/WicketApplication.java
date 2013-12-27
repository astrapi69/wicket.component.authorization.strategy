package component.authorization.strategy.example;
import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;


public class WicketApplication extends WebApplication {

	public Class<HomePage> getHomePage() {
		return HomePage.class;
	}

	public Session newSession(Request request, Response response) {
		return new WicketSession(request);
	}

	protected void init() {
		getSecuritySettings().setAuthorizationStrategy(
				new ApplicationAuthorizationStrategy());
	}
	
	private static List<User> users = new ArrayList<User>();
	public static List<User> getUsers() {
		return users;
	}
	static {
		User barman = new User("barman");
		barman.setPassword("secret");
		barman.add(ApplicationRight.VIEW_NAME);
		barman.add(ApplicationRight.EDIT_NAME);
		barman.add(ApplicationRight.VIEW_DESCRIPTION);
		users.add(barman);
		User waiter = new User("waiter");
		waiter.setPassword("secret");
		waiter.add(ApplicationRight.VIEW_NAME);
		users.add(waiter);
		User boss = new User("boss");
		boss.setPassword("secret");
		users.add(boss);
		boss.add(ApplicationRight.VIEW_NAME);
		boss.add(ApplicationRight.VIEW_DESCRIPTION);
		boss.add(ApplicationRight.EDIT_NAME);
		boss.add(ApplicationRight.EDIT_DESCRIPTION);
	}
}