package component.authorization.strategy.example;
import org.apache.wicket.Component;
import org.apache.wicket.RestartResponseAtInterceptPageException;
import org.apache.wicket.Session;
import org.apache.wicket.request.component.IRequestableComponent;

import authorization.strategy.api.AuthorizationStrategy;


public class ApplicationAuthorizationStrategy extends AuthorizationStrategy {

	@Override
	protected <T extends IRequestableComponent> boolean onInstantiationAuthorized(
			Class<T> componentClass) {
		if (((WicketSession) Session.get()).getUser() != null) {
			return true;
		}

		if (LoginPage.class.isAssignableFrom(componentClass)) {
			return true;
		}

		throw new RestartResponseAtInterceptPageException(LoginPage.class);
	}

	@Override
	protected boolean isEditable(Component component) {
		return ApplicationRights.bind(component).isEditable();
	}

	@Override
	protected boolean isRenderable(Component component) {
		return ApplicationRights.bind(component).isRenderable();
	}

}