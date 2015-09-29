import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class MatcherTest {
	
	@Test
	public void testMatchScore() {
		Matcher matcher = new Matcher();

		String[] listingTitleWords = {"panasonic", "lumix", "dmc", "fs10"};
		Set<String> productWords = new HashSet<String>();
		productWords.add("panasonic");
		productWords.add("fs10");
		
		assertEquals(0.5, matcher.matchScore(listingTitleWords, productWords), 0.0001);
		
	}

	@Test
	public void testSplitIntoNormalizedWords() {
		Matcher matcher = new Matcher();

		String[] words = {"panasonic", "lumix", "dmc", "fs10"};
		assertArrayEquals(words,
				matcher.splitIntoNormalizedWords("panasonic lumix dmc fs10      "));

	}

	@Test
	public void testNormalized() {
		Matcher matcher = new Matcher();
		assertEquals("panasonic lumix dmc fs10",
				matcher.normalize("Panasonic_Lumix_DMC-FS10"));

		assertEquals("olympus mju 5010",
				matcher.normalize("olympus µ[mju:]   5010"));
	}
}
