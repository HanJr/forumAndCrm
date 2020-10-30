package commandInstance;

public class ChangePwRequest {

	private String currentPw;
	private String newPw;
	private String confirmNewPw;
	
	public String getCurrentPw() {
		return currentPw;
	}
	public void setCurrentPw(String currentPw) {
		this.currentPw = currentPw;
	}
	public String getNewPw() {
		return newPw;
	}
	public void setNewPw(String newPw) {
		this.newPw = newPw;
	}
	public String getConfirmNewPw() {
		return confirmNewPw;
	}
	public void setConfirmNewPw(String confirmNewPw) {
		this.confirmNewPw = confirmNewPw;
	}
	public boolean matchingNewPw() {
		return newPw.equals(confirmNewPw);
	}
}
