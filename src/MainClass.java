import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class MainClass {

	private static final String RESULTS_FILE = "../resultsTest.txt";
	private static final String PRODUCTS_FILE = "../products.txt";
	private static final String LISTINGS_FILE = "../listings.txt";

	private static final String MODLE_KEY = "model";
	private static final String TITLE_KEY = "title";
	private static final String PRICE_KEY = "price";
	private static final String FAMILY_KEY = "family";
	private static final String LISTING_KEY = "listing";
	private static final String CURRENCY_KEY = "currency";
	private static final String ANNOUNCED_DATE = "announced-date";
	private static final String MANUFACTURER_KEY = "manufacturer";
	private static final String PRODUCT_NAME_KEY = "product_name";

	/**
	 * The entry point of the application
	 *
	 * @param args
	 * @return void
	 */
	public static void main(String[] args) {
		ArrayList<Product> products = readProducts();
		ArrayList<Listing> listings = readListings();
		
		Matcher matcher = new Matcher();
		
		HashMap<Product, List<Listing>> results = matcher.macheListings(
				products, listings);

		writeResultsToFile(results);
	}

	/**
	 * This method reads the products.txt file and for each json object in that
	 * file generate a Product Java object and returns a ArrayList containing
	 * all of those Java objects.
	 * 
	 * @return a ArrayList of generated products
	 */
	private static ArrayList<Product> readProducts() {
		ArrayList<Product> products = new ArrayList<Product>();

		try {
			BufferedReader reader = new BufferedReader(new FileReader(
					PRODUCTS_FILE));

			String line;
			while ((line = reader.readLine()) != null) {
				Product product = parseProductJsonString(line);

				if (!products.contains(product)) {
					products.add(product);
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return products;
	}

	/**
	 * This method reads the listings.txt file and for each json object in that
	 * file generate a Listing Java object and returns a ArrayList containing
	 * all of those Java objects. To parse each json object to a java object it
	 * uses {@link #parseListingJsonString(String listingJsonString)
	 * parseListingJsonString} method.
	 * 
	 * @return a ArrayList of generated listings
	 */
	private static ArrayList<Listing> readListings() {
		ArrayList<Listing> listings = new ArrayList<Listing>();

		try {
			BufferedReader reader = new BufferedReader(new FileReader(
					LISTINGS_FILE));

			String line;
			while ((line = reader.readLine()) != null) {
				Listing listing = parseListingJsonString(line);

				listings.add(listing);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return listings;
	}

	/**
	 * This method gets an ArrayList of Result java objects, convert each of
	 * them to a json object and writes it to results.txt file.
	 * 
	 * @param results
	 *            the list of result that must be written to the file
	 */
	private static void writeResultsToFile(
			HashMap<Product, List<Listing>> results) {

		try {
			PrintWriter out = new PrintWriter(RESULTS_FILE);

			for (Map.Entry<Product, List<Listing>> entry : results.entrySet()) {
				JSONObject object = new JSONObject();

				JSONArray listings = new JSONArray();

				List<Listing> listingsList = entry.getValue();

				for (Listing listing : entry.getValue()) {
					JSONObject listingJson = new JSONObject();

					listingJson.put(TITLE_KEY, listing.getTitle());
					listingJson.put(PRICE_KEY, listing.getPrice());
					listingJson.put(CURRENCY_KEY, listing.getCurrency());
					listingJson
							.put(MANUFACTURER_KEY, listing.getManufacturer());

					listings.add(listingJson);
				}

				object.put(LISTING_KEY, listings);
				object.put(PRODUCT_NAME_KEY, entry.getKey().getProductName());

				String jsonString = JSONValue.toJSONString(object);

				out.println(jsonString);
			}

			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method gets a string representing a listing json object, parses it
	 * and returns the equivalent Listing java object for the json object.
	 * 
	 * @param listingJsonString
	 *            the string that represent a listing json object
	 * @return Listing java object for listingJsonString
	 */
	static Listing parseListingJsonString(String listingJsonString) {
		String title = "";
		String manufacturer = "";
		String currency = "";
		String price = "";

		JSONParser parser = new JSONParser();

		JSONObject listingJson = null;

		try {
			listingJson = (JSONObject) parser.parse(listingJsonString);

			if (listingJson.containsKey(TITLE_KEY)) {
				title = ((String) listingJson.get(TITLE_KEY));
			}

			if (listingJson.containsKey(MANUFACTURER_KEY)) {
				manufacturer = ((String) listingJson.get(MANUFACTURER_KEY));
			}

			if (listingJson.containsKey(CURRENCY_KEY)) {
				currency = ((String) listingJson.get(CURRENCY_KEY));
			}

			if (listingJson.containsKey(PRICE_KEY)) {
				price = (String) listingJson.get(PRICE_KEY);
			}

		} catch (ParseException e) {
			e.printStackTrace();
		}

		Listing listing = new Listing(title, manufacturer, currency, price);

		return listing;
	}

	/**
	 * This method gets a string representing a product json object, parses it
	 * and returns the equivalent Product java object for the json object.
	 * 
	 * @param productJsonString
	 *            the string that represent a product json object
	 * @return product java object for productJsonString
	 */
	static Product parseProductJsonString(String productJsonString) {
		String productName = "";
		String manufacturer = "";
		String family = "";
		String model = "";
		String announcedDate = "";

		JSONParser parser = new JSONParser();

		JSONObject productJson = null;

		try {
			productJson = (JSONObject) parser.parse(productJsonString);

			if (productJson.containsKey(PRODUCT_NAME_KEY)) {
				productName = ((String) productJson.get(PRODUCT_NAME_KEY));
			}

			if (productJson.containsKey(MANUFACTURER_KEY)) {
				manufacturer = (String) productJson.get(MANUFACTURER_KEY);
			}

			if (productJson.containsKey(MODLE_KEY)) {
				model = (String) productJson.get(MODLE_KEY);
			}

			if (productJson.containsKey(FAMILY_KEY)) {
				family = (String) productJson.get(FAMILY_KEY);
			}

			if (productJson.containsKey(ANNOUNCED_DATE)) {
				announcedDate = (String) productJson.get(ANNOUNCED_DATE);
			}

		} catch (ParseException e) {
			e.printStackTrace();
		}

		Product product = new Product(productName, manufacturer, family, model,
				announcedDate);

		return product;
	}

	public static <T> void printProducts(ArrayList<T> objects) {
		for (Object object : objects) {
			System.out.println(object.toString());
		}
	}
}
