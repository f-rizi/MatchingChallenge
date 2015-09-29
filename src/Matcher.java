import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author fatemeh
 *
 */
public final class Matcher {
	
	private static final float SCORE_THRESHOLD = 0.7f;
	
	public HashMap<Product, List<Listing>> matchListings(
			List<Product> products, List<Listing> listings) {

			HashMap<Product, List<Listing>> results = new HashMap<Product, List<Listing>>();
			
			/* Pre-split products into words to reduce amount of work done in the main algorithm */
			Set<String>[] productNormalizedWords = new HashSet[products.size()];
			for (int i = 0; i < products.size(); i++) {
				Product product = products.get(i);
				String productWords[] = splitIntoNormalizedWords(
					     product.getProductName() + " " +
					     product.getManufacturer() + " " +
					     product.getModel() + " " +
					     product.getFamily());
				
				productNormalizedWords[i] = new HashSet<String>();
				for(String word : productWords)
					productNormalizedWords[i].add(word);
			}
			
			for (Listing listing : listings) {
				Product bestProduct = null;
				double bestScore = 0.0;

				String[] listingTitleWords = splitIntoNormalizedWords(listing.getTitle());

				/* find the product matching the listing */
				for (int i = 0; i < products.size(); i++) {
					double matchScore = matchScore(listingTitleWords, productNormalizedWords[i]);
					if (matchScore >= SCORE_THRESHOLD && matchScore > bestScore) {
						bestScore = matchScore;
						bestProduct = products.get(i);
					}
				}

				/* if we didn't find any matching product, skip this listing */
				if (bestProduct == null)
					continue;

				/* update result */
				List<Listing> matchedListings;
				if (results.containsKey(bestProduct)) {
					matchedListings = results.get(bestProduct);
				} else {
					matchedListings = new ArrayList<Listing>();
				}

				matchedListings.add(listing);
				results.put(bestProduct, matchedListings);
			}
			
			return results;
	}
	
	private float matchScore(String[] listingTitleWords, Set<String> productWords) {
		float relatedWords = 0;
		float totalWords = listingTitleWords.length;

		for (String listingTitleWord: listingTitleWords)
			if (productWords.contains(listingTitleWord))
				relatedWords++;

		return relatedWords / totalWords;
	}
	
	private String[] splitIntoNormalizedWords(String input) {
		return normalize(input).split(" ");
	}

	public static String normalize(String input) {
		String normalized =
				input.replaceAll("[/,_-]", " ")         /* replace punctuation by space */
					 .replaceAll("[^a-zA-Z0-9\\ ]", "") /* delete everything non-alphanumeric/space */
					 .replaceAll(" +", " ")             /* replace runs of spaces with a single space */
					 .toLowerCase().trim();
		return normalized;
	}
}
