package wicket.component.authorization.strategy;


import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.apache.wicket.authorization.Action;
import org.apache.wicket.authorization.IAuthorizationStrategy;
import org.apache.wicket.request.component.IRequestableComponent;

public abstract class AuthorizationStrategy implements IAuthorizationStrategy {
	public <T extends IRequestableComponent> boolean isInstantiationAuthorized(
			Class<T> componentClass) {
		if (!Page.class.isAssignableFrom(componentClass)) {
			return true;
		}
		return onInstantiationAuthorized();
	}

	protected abstract boolean onInstantiationAuthorized();

	public boolean isActionAuthorized(Component component, Action action) {
		if (action.equals(Component.RENDER)) {
			return Rights.boundWith(component).isVisible();
		} else if (action.equals(Component.ENABLE)) {
			return Rights.boundWith(component).isEnabled();
		}
		return true;
	}


}
