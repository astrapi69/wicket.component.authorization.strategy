package component.authorization.strategy.example;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;


public class UserDataPanel extends Panel
{

	private static final long serialVersionUID = 1L;

	public UserDataPanel(final String id)
	{
		this(id, null);
	}

	public UserDataPanel(final String id, final IModel<?> model)
	{
		super(id, model);
		final IModel<UserData> userDataModel = new CompoundPropertyModel<UserData>(new UserData());
		final Form<UserData> form = new Form<UserData>("form", userDataModel);
		add(form);
		final TextField<String> nameTextField = new TextField<String>("name");
		nameTextField.setRequired(true);
		ApplicationRights.bind(nameTextField).renderable(ApplicationRight.VIEW_NAME)
			.editable(ApplicationRight.EDIT_NAME);
		form.add(nameTextField);
		final TextField<String> roleTextField = new TextField<String>("role");
		form.add(roleTextField);

		final TextField<String> description = new TextField<String>("description");
		ApplicationRights.bind(description).renderable(ApplicationRight.VIEW_DESCRIPTION)
			.editable(ApplicationRight.EDIT_DESCRIPTION);
		form.add(description);
	}

}