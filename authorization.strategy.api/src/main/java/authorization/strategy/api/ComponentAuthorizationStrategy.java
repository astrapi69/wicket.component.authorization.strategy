package authorization.strategy.api;

import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.apache.wicket.authorization.Action;
import org.apache.wicket.authorization.IAuthorizationStrategy;
import org.apache.wicket.request.component.IRequestableComponent;

/**
 * An authorization strategy which allows to secure components so they can be
 * enabled, disabled or invisible if the user does have or have not the given rights.
 */
public abstract class ComponentAuthorizationStrategy implements
		IAuthorizationStrategy {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isActionAuthorized(Component component, Action action) {
		if (action.equals(Component.RENDER)) {
			return isRenderable(component);
		} else if (action.equals(Component.ENABLE)) {
			return isEditable(component);
		}
		return true;
	}

	/**
	 * Checks if the given component is editable.
	 * 
	 * @param component
	 *            the component
	 * @return true, if the given component is editable, otherwise false
	 */
	protected abstract boolean isEditable(Component component);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <T extends IRequestableComponent> boolean isInstantiationAuthorized(
			Class<T> componentClass) {
		if (!Page.class.isAssignableFrom(componentClass)) {
			return true;
		}
		return onInstantiationAuthorized(componentClass);
	}

	/**
	 * Checks if the given component is renderable.
	 * 
	 * @param component
	 *            the component
	 * @return true, if the given component is renderable, otherwise false
	 */
	protected abstract boolean isRenderable(Component component);

	/**
	 * This callback method is invoked at the end of the method
	 * {@link ComponentAuthorizationStrategy#isInstantiationAuthorized(Class)}.
	 * Here you can call special Authorization logic.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param componentClass
	 *            the component class
	 * @return true, if successful
	 */
	protected abstract <T extends IRequestableComponent> boolean onInstantiationAuthorized(
			Class<T> componentClass);

}