package component.authorization.strategy.example;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import authorization.strategy.api.Right;


public class User implements Serializable
{
	private static final long serialVersionUID = 1L;
	String password;
	Set<Right> rights = new HashSet<Right>();
	String username;

	public User(final String username)
	{
		this.username = username;
	}

	public void add(final Right right)
	{
		rights.add(right);
	}

	public String getPassword()
	{
		return password;
	}

	public String getUsername()
	{
		return username;
	}

	public boolean isAuthorized(final Right right)
	{
		return rights.contains(right);
	}

	public void setPassword(final String password)
	{
		this.password = password;
	}

	public void setUsername(final String username)
	{
		this.username = username;
	}

}