package component.authorization.strategy.example;

import java.io.Serializable;

public class UserData implements Serializable
{

	private static final long serialVersionUID = 1L;
	String description;
	String name;
	String role;

	public String getDescription()
	{
		return description;
	}

	public String getName()
	{
		return name;
	}

	public String getRole()
	{
		return role;
	}

	public void setDescription(final String description)
	{
		this.description = description;
	}

	public void setName(final String name)
	{
		this.name = name;
	}

	public void setRole(final String role)
	{
		this.role = role;
	}
}