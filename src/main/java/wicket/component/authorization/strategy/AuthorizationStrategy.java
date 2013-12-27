package wicket.component.authorization.strategy;


import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.apache.wicket.authorization.Action;
import org.apache.wicket.authorization.IAuthorizationStrategy;
import org.apache.wicket.request.component.IRequestableComponent;

public abstract class AuthorizationStrategy implements IAuthorizationStrategy {
	public boolean isActionAuthorized(Component component, Action action) {
		if (action.equals(Component.RENDER)) {
			return isRenderable(component);
		} else if (action.equals(Component.ENABLE)) {
			return isEditable(component);
		}
		return true;
	}

	protected abstract boolean isEditable(Component component);

	public <T extends IRequestableComponent> boolean isInstantiationAuthorized(
			Class<T> componentClass) {
		if (!Page.class.isAssignableFrom(componentClass)) {
			return true;
		}
		return onInstantiationAuthorized(componentClass);
	}

	protected abstract boolean isRenderable(Component component);

	protected abstract <T extends IRequestableComponent> boolean onInstantiationAuthorized(Class<T> componentClass);

}
