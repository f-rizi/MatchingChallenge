

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * This CLass represents Product entity
 *
 *@author  Fatemeh
 */
public class Product {
	private String productName = "";
	private String manufacturer = "";
	private String family = "";
	private String model = "";
	private String announcedDate = "";

	public Product(String productName, String manufacturer, String family,
			String model, String announcedDate) {
		
		this.productName = productName;
		this.manufacturer = manufacturer;
		this.family = family;
		this.model = model;
		this.announcedDate = announcedDate;
	}

	public String getProductName() {
		return productName;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public String getFamily() {
		return family;
	}

	public String getModel() {
		return model;
	}

	public String getAnnouncedDate() {
		return announcedDate;
	}
	
	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Product)) {
			return false;
		}
		
		if (this == object) {
			return true;
		}
		
		Product product = (Product) object;
		
		return new EqualsBuilder().
	            append(Matcher.normalize(productName) , Matcher.normalize(product.productName)).
	            append(Matcher.normalize(manufacturer), Matcher.normalize(product.manufacturer)).
	            append(Matcher.normalize(family), Matcher.normalize(product.family)).
	            append(Matcher.normalize(model), Matcher.normalize(product.model)).
	            append(announcedDate, product.announcedDate).
	            isEquals();
	}
	
	@Override
	public int hashCode() {
		
		return new HashCodeBuilder(17, 31).
	            append(Matcher.normalize(productName)).
	            append(Matcher.normalize(manufacturer)).
	            append(Matcher.normalize(model)).
	            append(Matcher.normalize(family)).
	            append(Matcher.normalize(announcedDate)).toHashCode();
	}
	
	@Override
	public String toString() {
		StringBuilder description = new StringBuilder();

		description.append(productName);
		description.append(", ");

		description.append(manufacturer);
		description.append(", ");

		description.append(model);
		description.append(", ");

		description.append(family);
		description.append(", ");

		description.append(announcedDate);

		return description.toString();
	}
}