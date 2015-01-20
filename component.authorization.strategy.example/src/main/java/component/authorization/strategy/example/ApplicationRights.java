package component.authorization.strategy.example;

import java.util.HashSet;

import org.apache.wicket.Component;
import org.apache.wicket.MetaDataKey;

import authorization.strategy.api.Right;
import authorization.strategy.api.Rights;

public class ApplicationRights extends Rights {

	private static final long serialVersionUID = 1L;

	public static ApplicationRights bind(Component component) {
		return new ApplicationRights(component);
	}
	
	protected ApplicationRights(Component component) {
		super(component);
	}

	protected boolean onAuthorized(MetaDataKey<HashSet<Right>> key) {
		HashSet<Right> componentRights = component.getMetaData(key);
		if (componentRights == null) {
			return true;
		}

		WicketSession session = (WicketSession) component.getSession();

		for (Right right : componentRights) {
			if (session.isAuthorized(right)) {
				return true;
			}
		}
		return false;
	}

}