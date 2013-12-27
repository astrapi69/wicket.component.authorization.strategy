package wicket.component.authorization.strategy;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;

import org.apache.wicket.Component;
import org.apache.wicket.MetaDataKey;

public abstract class Rights implements Serializable {
	private static final long serialVersionUID = 1L;
	private static MetaDataKey<HashSet<Right>> EDITABLE = new MetaDataKey<HashSet<Right>>() {
		private static final long serialVersionUID = 1L;
	};
	private static MetaDataKey<HashSet<Right>> RENDERABLE = new MetaDataKey<HashSet<Right>>() {
		private static final long serialVersionUID = 1L;
	};

	protected final Component component;

	protected Rights(Component component) {
		this.component = component;
	}

	private void add(MetaDataKey<HashSet<Right>> key, Right... rights) {

		if (rights.length == 0) {
			component.setMetaData(key, null);
		} else {
			HashSet<Right> componentRights = component.getMetaData(key);
			if (componentRights == null) {
				componentRights = new HashSet<Right>();
				component.setMetaData(key, componentRights);
			}
			componentRights.clear();
			componentRights.addAll(Arrays.asList(rights));
		}
	}

	public boolean isAuthorized(MetaDataKey<HashSet<Right>> key) {
		return onAuthorized(key);
	}

	protected abstract boolean onAuthorized(MetaDataKey<HashSet<Right>> key);

	public Rights editable(Right... rights) {
		add(EDITABLE, rights);
		return this;
	}

	public boolean isEditable() {
		return isAuthorized(EDITABLE);
	}

	public boolean isRenderable() {
		return isAuthorized(RENDERABLE);
	}

	public Rights renderable(Right... rights) {
		add(RENDERABLE, rights);
		return this;
	}

}
