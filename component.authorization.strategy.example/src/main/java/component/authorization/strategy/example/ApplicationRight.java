package component.authorization.strategy.example;

import authorization.strategy.api.Right;

public class ApplicationRight implements Right
{

	private static final long serialVersionUID = 1L;

	public static final ApplicationRight EDIT_DESCRIPTION = new ApplicationRight();

	public static final ApplicationRight EDIT_NAME = new ApplicationRight();

	public static final ApplicationRight VIEW_DESCRIPTION = new ApplicationRight();

	public static final ApplicationRight VIEW_NAME = new ApplicationRight();

	private ApplicationRight()
	{
	}

}