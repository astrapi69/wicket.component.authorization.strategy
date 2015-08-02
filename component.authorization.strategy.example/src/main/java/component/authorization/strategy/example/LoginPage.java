package component.authorization.strategy.example;

import org.apache.wicket.markup.html.WebPage;

public class LoginPage extends WebPage
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LoginPage()
	{
		add(new LoginPanel("loginPanel"));
	}
}