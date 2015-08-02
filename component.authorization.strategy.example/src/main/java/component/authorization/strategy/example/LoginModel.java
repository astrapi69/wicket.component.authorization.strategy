package component.authorization.strategy.example;

import java.io.Serializable;

public class LoginModel implements Serializable
{

	private static final long serialVersionUID = 1L;
	private String password;
	private String username;

	public String getPassword()
	{
		return password;
	}

	public String getUsername()
	{
		return username;
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
