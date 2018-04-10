package admin;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import client.InteractDatabase;
import java.io.Serializable;

/**
 * A class the handles communications with login online page.
 * @author Janty Azmat
 */
@Named
@SessionScoped
public class LoginBean implements Serializable {
	// Fields
	private static final long serialVersionUID = -1976088987121401235L;
	private InteractDatabase interactDatabase = new InteractDatabase();
	private String loginUserName = ""; // Will hold the login user name from the login page
	private String loginPassword = ""; // Will hold the login password from the login page
	private String displayText = ""; // A string to display the result of checking login in the login page


	/**
	 * Resets the fields in case of logout (return the login page name for now)
	 * @return	the name of the page to go to after logout
	 */
	public String logout() {
		//loginUserName = loginPassword = displayText = "";
		//section = "Orders";
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "login?faces-redirect=true";
	}

	/**
	 * Checks if the person accessing the page is logged in admin or not and redirects accordingly. Plus it takes a parameter to
	 * specify if calling this method is a result of a failed login (so 'displayFailed' should be 'true') to display a failed
	 * login message, or is calling this method is the first access to page (so 'displayFailed' should be 'false')
	 * @param displayFailed	specify if we want to display some kind of error while logging in or not.
	 * @return	returns the page to redirect to.
	 */
	public String checkAdminRedirect(boolean displayFailed) {
		displayText = "";
		if (this.loginUserName.isEmpty() && this.loginPassword.isEmpty()) { // Most surely happens when the page is first visited
			return "login"; // No need to change 'displayText' here
		} else if (this.interactDatabase.checkLogin(this.loginUserName, this.loginPassword)) { // Check for login with DAO object
			if (displayFailed)
				return "admin"; // Go to admin page if login is successful.
			return "";
		} else {
			if (displayFailed) // Check if we need to display some login failed message.
				displayText = "Login failed.. Check your credentials.";
			return "login"; // Go to login page if login failed or if this is the first time accessing.
		}
	}

	/**
	 * @return	the loginUserName
	 */
	public String getLoginUserName() {
		return loginUserName;
	}

	/**
	 * @param loginUserName the loginUserName to set
	 */
	public void setLoginUserName(String loginUserName) {
		this.loginUserName = loginUserName;
	}

	/**
	 * @return	the loginPassword
	 */
	public String getLoginPassword() {
		return loginPassword;
	}

	/**
	 * @param loginPassword the loginPassword to set
	 */
	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}

	/**
	 * @return	the displayText
	 */
	public String getDisplayText() {
		return displayText;
	}

	/**
	 * @param displayText the displayText to set
	 */
	public void setDisplayText(String displayText) {
		this.displayText = displayText;
	}

}
