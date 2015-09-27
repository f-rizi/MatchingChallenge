import static org.junit.Assert.*;

import org.junit.Test;


public class MainClassTest {

	@Test
	public void testParseListingJsonString() {
		Listing listing;
		String listingJsonString;
		
		listing = new Listing("LEGO Ninjago - Ambush - 2258", "LEGO", "GBP", "21.99");
		listingJsonString = "{\"title\":\"LEGO Ninjago - Ambush - 2258\",\"manufacturer\":\"LEGO\",\"currency\":\"GBP\",\"price\":\"21.99\"}";
		
		assertEquals(listing, MainClass.parseListingJsonString(listingJsonString));
		
		
		listing = new Listing("LEGO Ninjago - Ambush - 2858", "LEGO", "GBP", "21.99");
		listingJsonString = "{\"title\":\"LEGO Ninjago - Ambush - 2258\",\"manufacturer\":\"LEGO\",\"currency\":\"GBP\",\"price\":\"21.99\"}";
		
		assertNotEquals(listing, MainClass.parseListingJsonString(listingJsonString));
		
		
		listing = new Listing("LEGO Ninjago - Ambush - 2258", "LEGO", "GBP", "21.99");
		listingJsonString = "{\"title\":\"LEGO Ninjago - Ambush - 2258\", \"currency\":\"GBP\",\"price\":\"21.99\"}";
		
		assertNotEquals(listing, MainClass.parseListingJsonString(listingJsonString));

	}

	@Test
	public void testParseProductJsonString() {
		Product product;
		String productJsonString;
		
		
		product = new Product("Pentax-WG-1", "Pentax",
				"Optio", "WG-1", "2010-09-19T20:00:00.000-04:00");
		
		productJsonString = "{\"product_name\":\"Pentax-WG-1\",\"manufacturer\":\"Pentax\",\"model\":\"WG-1\",\"family\":\"Optio\",\"announced-date\":\"2010-09-19T20:00:00.000-04:00\"}";
		
		assertEquals(product,
				MainClass.parseProductJsonString(productJsonString));
		
		
		product = new Product("Pentax-WG-2", "Pentax",
				"Optio", "WG-1", "2010-09-19T20:00:00.000-04:00");
		
		productJsonString = "{\"product_name\":\"Pentax-WG-1\",\"manufacturer\":\"Pentax\",\"model\":\"WG-1\",\"family\":\"Optio\",\"announced-date\":\"2010-09-19T20:00:00.000-04:00\"}";
		
		assertNotEquals(product,
				MainClass.parseProductJsonString(productJsonString));
		
		
		product = new Product("Pentax-WG-1", "Pentax",
				"", "WG-1", "2010-09-19T20:00:00.000-04:00");
		
		productJsonString = "{\"product_name\":\"Pentax-WG-1\",\"manufacturer\":\"Pentax\",\"model\":\"WG-1\",\"announced-date\":\"2010-09-19T20:00:00.000-04:00\"}";
		
		assertEquals(product,
				MainClass.parseProductJsonString(productJsonString));
	}

}
