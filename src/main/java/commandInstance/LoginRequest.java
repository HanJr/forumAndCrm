package commandInstance;

public class LoginRequest {

	private String email;
	private String password;
	private boolean rememberEmail;
	
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public String getPassword() {
		return password;
	}
	
	public void setRememberEmail(boolean rememberEmail) {
		this.rememberEmail = rememberEmail;
	}
	
	public boolean isRememberEmail() {
		return rememberEmail;
	}
}
