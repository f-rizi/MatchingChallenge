import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class MatcherTest {

	@Test
	public void testGetResults() {
	}

	@Test
	public void testDoseMatch() {
		Product product;
		Listing listing;
		
		product = new Product("Panasonic_Lumix_DMC-FS10", "Panasonic",
				"DMC-FS10", "Lumix", "2010-01-05T19:00:00.000-05:00");
		
		listing = new Listing(
				"Panasonic Lumix FS10 Digital Camera - Pink (12.1MP, 5x Optical Zoom) 2.7 inch LCD",
				"Panasonic", "GBP", "90.99");

//		assertFalse(Matcher.doseMatch(product, listing));
		
		
		product = new Product("Panasonic_Lumix_FS10", "Panasonic",
				"FS10", "Lumix", "2010-01-05T19:00:00.000-05:00");
		
		listing = new Listing(
				"Panasonic Lumix FS10 Digital Camera - Pink (12.1MP, 5x Optical Zoom) 2.7 inch LCD",
				"Panasonic", "GBP", "90.99");

//		assertTrue(Matcher.doseMatch(product, listing));
		
		product = new Product("Panasonic_Lumix_FS10", "Panasonic",
				"FS10", "Lumix", "2010-01-05T19:00:00.000-05:00");
		
		listing = new Listing(
				"Panasonic! Lumix FS10 Digital Camera - Pink (12.1MP, 5x Optical Zoom) 2.7 inch LCD",
				"Panasonic", "GBP", "90.99");

//		assertTrue(Matcher.doseMatch(product, listing));
		
		product = new Product("Canon_EOS_550D", "Canon",
				"550D", "EOS", "2010-02-07T19:00:00.000-05:00");
		
		listing = new Listing(
				"CANON EOS 550D + EF-S 18-135 mm IS Lens",
				"Canon", "GBP", "1072.02");

//		assertTrue(Matcher.doseMatch(product, listing));
		
		
		product = new Product("Panasonic_Lumix_DMC-FS10", "Panasonic",
				"DMC-FS10", "Lumix", "2010-01-05T19:00:00.000-05:00");
		
		listing = new Listing(
				"Panasonic Lumix FS10 Digital Camera - Pink body only",
				"Panasonic", "GBP", "90.99");
		
//		assertFalse(Matcher.doseMatch(product, listing));
		
		
		product = new Product("Canon_EOS_550D", "Canon",
				"550D", "EOS", "2010-01-05T19:00:00.000-05:00");
		
		listing = new Listing("CANON 550D + EF-S 18-55mm IS lens & EF-S 55-250mm IS lens + Accessories Kit for Canon Eos 550D cameras",
				"Canon","GBP","1069.99");
		
//		assertFalse(Matcher.doseMatch(product, listing));
		
		
		listing = new Listing(
				"Panasonic Lumix DMC FS10 Digital Camera body only for Panasonic Lumix DMC FS10",
				"Panasonic", "GBP", "90.99");
		
		product = new Product("Panasonic_Lumix_DMC-FS10", "Panasonic",
				"DMC-FS10", "Lumix", "2010-01-05T19:00:00.000-05:00");
		
//		assertFalse(Matcher.doseMatch(product, listing));
		
		
		listing = new Listing(
				"olympus µ[mju:] 5010",
				"Olympus", "GBP", "291.14");
		
		product = new Product("Olympus_mju_5010", "Olympus",
				"mju 5010", "", "2010-01-05T19:00:00.000-05:00");
		
//		assertTrue(Matcher.doseMatch(product, listing));

	}
}
