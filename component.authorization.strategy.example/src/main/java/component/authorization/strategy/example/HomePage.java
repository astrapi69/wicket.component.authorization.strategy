package component.authorization.strategy.example;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class HomePage extends WebPage
{

	private static final long serialVersionUID = 1L;

	public HomePage(final PageParameters parameters)
	{
		final UserDataPanel userDataPanel = new UserDataPanel("userDataPanel");
		add(userDataPanel);
	}
}