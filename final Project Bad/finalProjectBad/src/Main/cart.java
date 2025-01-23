package Main;

public class cart {
  private int userID;
  private int watchID;
  private int Quantity;
  
public cart(int userID, int watchID, int quantity) {
	super();
	this.userID = userID;
	this.watchID = watchID;
	Quantity = quantity;
}

public int getUserID() {
	return userID;
}

public void setUserID(int userID) {
	this.userID = userID;
}

public int getWatchID() {
	return watchID;
}

public void setWatchID(int watchID) {
	this.watchID = watchID;
}

public int getQuantity() {
	return Quantity;
}

public void setQuantity(int quantity) {
	Quantity = quantity;
}



  
  
  

  
  
}

