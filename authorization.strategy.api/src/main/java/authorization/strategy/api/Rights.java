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

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;

import org.apache.wicket.Component;
import org.apache.wicket.MetaDataKey;

/**
 * The Class Rights decorates a component and adds Right objects to it.
 */
public abstract class Rights implements Serializable
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant MetaDataKey that marks if a component is editable. */
	private static MetaDataKey<HashSet<Right>> EDITABLE = new MetaDataKey<HashSet<Right>>()
	{
		private static final long serialVersionUID = 1L;
	};

	/** The Constant MetaDataKey that marks if a component is renderable. */
	private static MetaDataKey<HashSet<Right>> RENDERABLE = new MetaDataKey<HashSet<Right>>()
	{
		private static final long serialVersionUID = 1L;
	};

	/** The component for add Right objects. */
	protected final Component component;

	/**
	 * Instantiates a new rights.
	 *
	 * @param component
	 *            the component for add Right objects.
	 */
	protected Rights(final Component component)
	{
		this.component = component;
	}

	/**
	 * Adds the given Right objects to this component for the given MetaDataKey.
	 *
	 * @param key
	 *            the MetaDataKey key
	 * @param rights
	 *            the Right objects to add
	 */
	private void add(final MetaDataKey<HashSet<Right>> key, final Right... rights)
	{

		if (rights.length == 0)
		{
			component.setMetaData(key, null);
		}
		else
		{
			HashSet<Right> componentRights = component.getMetaData(key);
			if (componentRights == null)
			{
				componentRights = new HashSet<Right>();
				component.setMetaData(key, componentRights);
			}
			componentRights.clear();
			componentRights.addAll(Arrays.asList(rights));
		}
	}

	/**
	 * Adds the given Right objects to this component for the MetaDataKey {@link Rights#EDITABLE}
	 *
	 * @param rights
	 *            the Right objects to add
	 * @return this object.
	 */
	public Rights editable(final Right... rights)
	{
		add(EDITABLE, rights);
		return this;
	}

	/**
	 * Checks if this component is authorized for the given MetaDataKey.
	 *
	 * @param key
	 *            the MetaDataKey object
	 * @return true, if this component is authorized, otherwise false
	 */
	public boolean isAuthorized(final MetaDataKey<HashSet<Right>> key)
	{
		return onAuthorized(key);
	}

	/**
	 * Checks if this component is editable.
	 *
	 * @return true, if is editable
	 */
	public boolean isEditable()
	{
		return isAuthorized(EDITABLE);
	}

	/**
	 * Checks if this component is renderable.
	 *
	 * @return true, if is renderable
	 */
	public boolean isRenderable()
	{
		return isAuthorized(RENDERABLE);
	}

	/**
	 * On authorized.
	 *
	 * @param key
	 *            the key
	 * @return true, if successful
	 */
	protected abstract boolean onAuthorized(final MetaDataKey<HashSet<Right>> key);

	/**
	 * Adds the given Right objects to this component for the MetaDataKey {@link Rights#RENDERABLE}
	 *
	 * @param rights
	 *            the Right objects to add
	 * @return this object
	 */
	public Rights renderable(final Right... rights)
	{
		add(RENDERABLE, rights);
		return this;
	}

}
