

import java.util.ArrayList;

/**
 * This CLass represents Result entity
 *
 *@author  Fatemeh
 */

public class Result {
	
	private String productName;
	private ArrayList<Listing> listings;
	
	public Result() {}
	
	public Result(String productName, ArrayList<Listing> listings) {
		this.productName = productName;
		this.listings = listings;
	}
	
	public String getProductName() {
		return productName;
	}
	public ArrayList<Listing> getListings() {
		return listings;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public void setListings(ArrayList<Listing> listings) {
		this.listings = listings;
	}

	@Override
	public String toString() {
		StringBuilder description = new StringBuilder();
		
		description.append(productName);
		description.append(" : [");
		
		for (Listing listing : listings) {
			description.append(listing.toString());
			description.append(", ");
		}
		
		description.append("]");
		
		return description.toString();
	}
}
