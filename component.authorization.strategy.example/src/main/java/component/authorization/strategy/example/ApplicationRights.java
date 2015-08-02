package component.authorization.strategy.example;

import java.util.HashSet;

import org.apache.wicket.Component;
import org.apache.wicket.MetaDataKey;

import authorization.strategy.api.Right;
import authorization.strategy.api.Rights;

public class ApplicationRights extends Rights
{

	private static final long serialVersionUID = 1L;

	public static ApplicationRights bind(final Component component)
	{
		return new ApplicationRights(component);
	}

	protected ApplicationRights(final Component component)
	{
		super(component);
	}

	@Override
	protected boolean onAuthorized(final MetaDataKey<HashSet<Right>> key)
	{
		final HashSet<Right> componentRights = component.getMetaData(key);
		if (componentRights == null)
		{
			return true;
		}

		final WicketSession session = (WicketSession)component.getSession();

		for (final Right right : componentRights)
		{
			if (session.isAuthorized(right))
			{
				return true;
			}
		}
		return false;
	}

}