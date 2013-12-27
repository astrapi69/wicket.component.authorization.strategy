package component.authorization.strategy.example;
import java.io.Serializable;

public class UserData implements Serializable {
	
	private static final long serialVersionUID = 1L;
	String name;
	String role;
	String description;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}