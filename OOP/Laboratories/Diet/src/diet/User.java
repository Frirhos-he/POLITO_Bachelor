package diet;

/**
 * Represent a take-away system user
 *  
 */
public class User {
		
	private String first;
	private String last;
	private String email;
	private String phone;
	
	/**
	 * get user's last name
	 * @return last name
	 */
	public String getLastName() {
		return last;
	}
	
	public User(String first, String last, String email, String phone) {
		this.first = first;
		this.last = last;
		this.email = email;
		this.phone = phone;
	}

	/**
	 * get user's first name
	 * @return first name
	 */
	public String getFirstName() {
		return first;
	}
	
	/**
	 * get user's email
	 * @return email
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * get user's phone number
	 * @return  phone number
	 */
	public String getPhone() {
		return phone;
	}
	
	/**
	 * change user's email
	 * @param email new email
	 */
	public void SetEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		return first + " " + last;  // prof said first last
	}

	/**
	 * change user's phone number
	 * @param phone new phone number
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}
