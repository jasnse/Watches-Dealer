package Main;

public class watches {
	private int WatchID;
	private int BrandID;
	private String WatchName;
	private int WatchPrice;
	private int WatchStock;
	private String watchbrand;
	
	public watches(int watchID, int brandID, String watchName, int watchPrice, int watchStock, String watchbrand) {
		super();
		WatchID = watchID;
		BrandID = brandID;
		WatchName = watchName;
		WatchPrice = watchPrice;
		WatchStock = watchStock;
		this.watchbrand = watchbrand;
	}

	public int getWatchID() {
		return WatchID;
	}

	public void setWatchID(int watchID) {
		WatchID = watchID;
	}

	public int getBrandID() {
		return BrandID;
	}

	public void setBrandID(int brandID) {
		BrandID = brandID;
	}

	public String getWatchName() {
		return WatchName;
	}

	public void setWatchName(String watchName) {
		WatchName = watchName;
	}

	public int getWatchPrice() {
		return WatchPrice;
	}

	public void setWatchPrice(int watchPrice) {
		WatchPrice = watchPrice;
	}

	public int getWatchStock() {
		return WatchStock;
	}

	public void setWatchStock(int watchStock) {
		WatchStock = watchStock;
	}

	public String getWatchbrand() {
		return watchbrand;
	}

	public void setWatchbrand(String watchbrand) {
		this.watchbrand = watchbrand;
	}
	
	

	
	

	
	

}
