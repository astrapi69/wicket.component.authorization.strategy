package component.authorization.strategy.example;

/**
 * The Class SpecialRight that represents a specific application right from a db or somewhere else.
 */
public class SpecialRight
{

	/** The Constant VIEW_DESCRIPTION. */
	public static final SpecialRight VIEW_DESCRIPTION = new SpecialRight();

	/** The Constant EDIT_DESCRIPTION. */
	public static final SpecialRight EDIT_DESCRIPTION = new SpecialRight();

	/** The Constant VIEW_NAME. */
	public static final SpecialRight VIEW_NAME = new SpecialRight();

	/** The Constant EDIT_NAME. */
	public static final SpecialRight EDIT_NAME = new SpecialRight();

	/**
	 * Instantiates a new special right.
	 */
	private SpecialRight()
	{
	}

}
