package variableObject;

public class AuthInfo {
	private String email;
	private String name;
	private int id;

	public AuthInfo(String email, String name, int id) {
		this.email = email;
		this.name = name;
		this.id = id;
	}	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
