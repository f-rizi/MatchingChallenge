

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;



/**
 * This CLass represents Listing entity
 *
 *@author  Fatemeh
 */
public class Listing {
	
	private String title = "";
	private String manufacturer = "";
	private String currency = "";
	private String price = "";
	
	public Listing(String title, String manufacturer, String currency, String price) {
		this.title = title;
		this.manufacturer = manufacturer;
		this.currency = currency;
		this.price = price;
	}
	
	public String getTitle() {
		return title;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public String getCurrency() {
		return currency;
	}

	public String getPrice() {
		return price;
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 31).
	            append(Matcher.normalize(title)).
	            append(Matcher.normalize(manufacturer)).
	            append(Matcher.normalize(currency)).
	            append(price).toHashCode();
	}
	
	@Override
	public boolean equals(Object object) {
		if (object instanceof Listing) {
			return false;
		}
		
		if (this == object) {
			return true;
		}
		
		Listing listing = (Listing) object;
		
		return new EqualsBuilder().
	            append(Matcher.normalize(title) , Matcher.normalize(listing.title)).
	            append(Matcher.normalize(manufacturer), Matcher.normalize(listing.manufacturer)).
	            append(Matcher.normalize(currency), Matcher.normalize(listing.currency)).
	            append(price, listing.price).
	            isEquals();
	}

	@Override
	public String toString() {
		StringBuilder description = new StringBuilder();
		
		description.append(title);
		description.append(", ");
		
		description.append(manufacturer);
		description.append(", ");
		
		description.append(currency);
		description.append(", ");
		
		description.append(price);
		
		return description.toString();
	}
}
