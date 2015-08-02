package component.authorization.strategy.example;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.protocol.http.WebApplication;

public class LoginPanel extends Panel
{

	private static final long serialVersionUID = 1L;

	public LoginPanel(final String id)
	{
		super(id);
		final FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
		add(feedbackPanel);
		final LoginModel model = new LoginModel();

		final CompoundPropertyModel<LoginModel> cpm = new CompoundPropertyModel<LoginModel>(model);

		final Form<LoginModel> form = new Form<LoginModel>("form", cpm);
		add(form);

		final TextField<String> username = new RequiredTextField<String>("username");
		form.add(username);
		final TextField<String> password = new PasswordTextField("password");
		password.setRequired(true);
		form.add(password);

		// Create submit button for the form
		final Button loginButton = new Button("loginButton")
		{
			/**
			 * The serialVersionUID.
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit()
			{
				if (((WicketSession)getSession()).login(model.getUsername(), model.getPassword()))
				{

					setResponsePage(WebApplication.get().getHomePage());
				}
				else
				{
					error("Username or password is invalid. Try again.");
				}
			}
		};
		form.add(loginButton);
	}

}
