package wicket.component.authorization.strategy;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;

import org.apache.wicket.Component;
import org.apache.wicket.MetaDataKey;

public class Rights implements Serializable {
	private static final long serialVersionUID = 1L;
	private static MetaDataKey<HashSet<Right>> ENABLED = new MetaDataKey<HashSet<Right>>() {
		private static final long serialVersionUID = 1L;
	};
	private static MetaDataKey<HashSet<Right>> VISIBLE = new MetaDataKey<HashSet<Right>>() {
		private static final long serialVersionUID = 1L;
	};

	public static Rights boundWith(Component component) {
		return new Rights(component);
	}

	private final Component component;

	protected Rights(Component component) {
		this.component = component;
	}

	private void add(MetaDataKey<HashSet<Right>> key, Right... rights) {

		if (rights.length == 0) {
			component.setMetaData(key, null);
		} else {
			HashSet<Right> stored = component.getMetaData(key);
			if (stored == null) {
				stored = new HashSet<Right>();
				component.setMetaData(key, stored);
			}
			stored.clear();
			stored.addAll(Arrays.asList(rights));
		}
	}

	public boolean isAuthorized(MetaDataKey<HashSet<Right>> key) {
		return onAuthorized(key);
	}

	protected boolean onAuthorized(MetaDataKey<HashSet<Right>> key) {
		return false;
	}

	public Rights enable(Right... rights) {
		add(ENABLED, rights);
		return this;
	}

	public boolean isEnabled() {
		return isAuthorized(ENABLED);
	}

	public boolean isVisible() {
		return isAuthorized(VISIBLE);
	}

	public Rights visible(Right... rights) {
		add(VISIBLE, rights);
		return this;
	}

}
