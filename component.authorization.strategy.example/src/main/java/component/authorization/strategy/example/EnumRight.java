package component.authorization.strategy.example;

import authorization.strategy.api.Right;

/**
 * The Enum EnumRight is an implementation from the interface Right as an enum that encapsulates
 * another Right. Sometimes the need is to decorate special application rights so we can give the
 * special application right as an argument to the enum object.
 */
public enum EnumRight implements Right
{

	/** The view description. */
	VIEW_DESCRIPTION(SpecialRight.VIEW_DESCRIPTION),

	/** The edit description. */
	EDIT_DESCRIPTION(SpecialRight.EDIT_DESCRIPTION),

	/** The view name. */
	VIEW_NAME(SpecialRight.VIEW_NAME),

	/** The edit name. */
	EDIT_NAME(SpecialRight.EDIT_NAME);

	/** The right. */
	private final SpecialRight right;

	/**
	 * Instantiates a new enum right.
	 * 
	 * @param right
	 *            the right
	 */
	private EnumRight(final SpecialRight right)
	{
		this.right = right;
	}

	/**
	 * Gets the right.
	 * 
	 * @return the right
	 */
	public SpecialRight getRight()
	{
		return right;
	}
}
