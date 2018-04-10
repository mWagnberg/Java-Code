package admin;

/**
 * A class that represents an administrator for the web-shop project
 * @author Janty Azmat
 */
public class Admin {
	// Fields
	private String userName;
	private String password = "XXXXXXXXXXXXXXXXXXXX";
	private boolean inEdit = false; // An inside field to check if the Admin object is in the edit state in the admin-pages tables

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Check if the Admin object is in the edit state in the admin-pages tables
	 * @return the inEdit
	 */
	public boolean getInEdit() {
		return inEdit;
	}

	/**
	 * Sets if the Admin object is in the edit state in the admin-pages tables
	 * @param inEdit the inEdit to set
	 */
	public void setInEdit(boolean inEdit) {
		this.inEdit = inEdit;
	}

}
