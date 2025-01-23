package Main;

public class detailTransaction {
	private int transactionID;
	private int watchID;
	private String watchName;
	private String watchBrand;
	private int watchPrice;
	private int quantity;
	private int subTotal;
	
	public detailTransaction(int transactionID, int watchID, String watchName, String watchBrand, int watchPrice,
			int quantity, int subTotal) {
		super();
		this.transactionID = transactionID;
		this.watchID = watchID;
		this.watchName = watchName;
		this.watchBrand = watchBrand;
		this.watchPrice = watchPrice;
		this.quantity = quantity;
		this.subTotal = subTotal;
	}

	public int getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(int transactionID) {
		this.transactionID = transactionID;
	}

	public int getWatchID() {
		return watchID;
	}

	public void setWatchID(int watchID) {
		this.watchID = watchID;
	}

	public String getWatchName() {
		return watchName;
	}

	public void setWatchName(String watchName) {
		this.watchName = watchName;
	}

	public String getWatchBrand() {
		return watchBrand;
	}

	public void setWatchBrand(String watchBrand) {
		this.watchBrand = watchBrand;
	}

	public int getWatchPrice() {
		return watchPrice;
	}

	public void setWatchPrice(int watchPrice) {
		this.watchPrice = watchPrice;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(int subTotal) {
		this.subTotal = subTotal;
	}
	
	
	
	
	
	
	
}

