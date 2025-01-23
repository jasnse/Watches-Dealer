package Main;

public class User {
private int userID;
private String userName;
private String userEmail;
private String userPassword;
private String userGender;
private String userRole;

public User(int userID, String userName, String userEmail, String userPassword, String userGender, String userRole) {
	super();
	this.userID = userID;
	this.userName = userName;
	this.userEmail = userEmail;
	this.userPassword = userPassword;
	this.userGender = userGender;
	this.userRole = userRole;
}
public int getUserID() {
	return userID;
}
public void setUserID(int userID) {
	this.userID = userID;
}
public String getUserName() {
	return userName;
}
public void setUserName(String userName) {
	this.userName = userName;
}
public String getUserEmail() {
	return userEmail;
}
public void setUserEmail(String userEmail) {
	this.userEmail = userEmail;
}
public String getUserPassword() {
	return userPassword;
}
public void setUserPassword(String userPassword) {
	this.userPassword = userPassword;
}
public String getUserGender() {
	return userGender;
}
public void setUserGender(String userGender) {
	this.userGender = userGender;
}
public String getUserRole() {
	return userRole;
}
public void setUserRole(String userRole) {
	this.userRole = userRole;
}



}

