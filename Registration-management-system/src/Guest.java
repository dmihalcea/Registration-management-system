
public class Guest {

	private String lastName;
	private String firstName;
	private String email;
	private String phoneNumber;
	
	Guest(String lastName, String firstName, String email, String phoneNumber) {
		this.lastName = lastName;
		this.firstName = firstName;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}
	
	public void setName(String lastName, String firstName) {
		this.lastName = lastName;
		this.firstName = firstName;
	}
	
	public String getName() {
		return (this.lastName + " " + this.firstName);
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public boolean isUsed(Guest gst) {
		if (this.getName().equalsIgnoreCase(gst.getName()) || 
			this.getEmail().equalsIgnoreCase(gst.getEmail()) || 
			this.getPhoneNumber().equalsIgnoreCase(gst.getPhoneNumber())) {
			return true;
		}
		return false;
	}
	
//	public boolean isNameUser(String name) {
//		if (this.getName().equalsIgnoreCase(name)) {
//			return true;
//		}
//		return false;
//	}
//	
//	public boolean isEmailUsed(String email) {
//		if (this.email.equalsIgnoreCase(email)) {
//			return true;
//		}
//		return false;
//	}
//	
//	public boolean isPhoneNumberUsed(String phoneNumber) {
//		if (this.phoneNumber.equalsIgnoreCase(phoneNumber)) {
//			return true;
//		}
//		return false;
//	}

}
