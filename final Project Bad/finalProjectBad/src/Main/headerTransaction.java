package Main;

public class headerTransaction {
private int transactionID;
private int userID;
private String transactionDate;

public headerTransaction(int transactionID, int userID, String transactionDate) {
	super();
	this.transactionID = transactionID;
	this.userID = userID;
	this.transactionDate = transactionDate;
}

public int getTransactionID() {
	return transactionID;
}

public void setTransactionID(int transactionID) {
	this.transactionID = transactionID;
}

public int getUserID() {
	return userID;
}

public void setUserID(int userID) {
	this.userID = userID;
}

public String getTransactionDate() {
	return transactionDate;
}

public void setTransactionDate(String transactionDate) {
	this.transactionDate = transactionDate;
}




}

