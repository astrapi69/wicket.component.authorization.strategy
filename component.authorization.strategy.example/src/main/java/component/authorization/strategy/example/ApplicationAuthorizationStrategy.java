package component.authorization.strategy.example;

import org.apache.wicket.Component;
import org.apache.wicket.RestartResponseAtInterceptPageException;
import org.apache.wicket.Session;
import org.apache.wicket.request.component.IRequestableComponent;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.IResource;

import authorization.strategy.api.ComponentAuthorizationStrategy;


public class ApplicationAuthorizationStrategy extends ComponentAuthorizationStrategy
{

	@Override
	protected boolean isEditable(final Component component)
	{
		return ApplicationRights.bind(component).isEditable();
	}

	@Override
	protected boolean isRenderable(final Component component)
	{
		return ApplicationRights.bind(component).isRenderable();
	}

	public boolean isResourceAuthorized(final IResource resource, final PageParameters parameters)
	{
		return false;
	}

	@Override
	protected <T extends IRequestableComponent> boolean onInstantiationAuthorized(
		final Class<T> componentClass)
	{
		if (((WicketSession)Session.get()).getUser() != null)
		{
			return true;
		}

		if (LoginPage.class.isAssignableFrom(componentClass))
		{
			return true;
		}

		throw new RestartResponseAtInterceptPageException(LoginPage.class);
	}

}