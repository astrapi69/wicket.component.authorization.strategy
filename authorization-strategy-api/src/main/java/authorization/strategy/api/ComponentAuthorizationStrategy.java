/**
 * Copyright (C) 2015 Asterios Raptis
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package authorization.strategy.api;

import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.apache.wicket.authorization.Action;
import org.apache.wicket.authorization.IAuthorizationStrategy;
import org.apache.wicket.request.component.IRequestableComponent;

/**
 * An authorization strategy which allows to secure components so they can be enabled, disabled or
 * invisible if the user does have or have not the given rights.
 */
public abstract class ComponentAuthorizationStrategy implements IAuthorizationStrategy
{

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isActionAuthorized(final Component component, final Action action)
	{
		if (action.equals(Component.RENDER))
		{
			return isRenderable(component);
		}
		else if (action.equals(Component.ENABLE))
		{
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
	protected abstract boolean isEditable(final Component component);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <T extends IRequestableComponent> boolean isInstantiationAuthorized(
		final Class<T> componentClass)
	{
		if (!Page.class.isAssignableFrom(componentClass))
		{
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
	protected abstract boolean isRenderable(final Component component);

	/**
	 * This callback method is invoked at the end of the method
	 * {@link ComponentAuthorizationStrategy#isInstantiationAuthorized(Class)}. Here you can call
	 * special Authorization logic.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param componentClass
	 *            the component class
	 * @return true, if successful
	 */
	protected abstract <T extends IRequestableComponent> boolean onInstantiationAuthorized(
		final Class<T> componentClass);

}