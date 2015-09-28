import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * @author fatemeh
 *
 */
public final class Matcher {
	
	private static final float SCORE_THRESHOLD = 0.60f;
	private static final float BALANCER = 1;

	
	public HashMap<Product, List<Listing>> macheListings(
			List<Product> products, List<Listing> listings) {

			HashMap<Product, List<Listing>> results = new HashMap<Product, List<Listing>>();

			for (Listing listing : listings) {
				Product bestProduct = null;
				double bestScore = 0.0;

				/* find the product matching the listing */
				for (Product product : products) {
					double matchScore = MatchScore(listing, product);
					
					if (matchScore >= SCORE_THRESHOLD && matchScore > bestScore) {
						bestScore = matchScore;
						bestProduct = product;
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
				
				//hadi injaa ro nemifaham chera intori neveshte boodi:
//				if (!results.containsKey(bestProduct))
//					reulsts.put();
			}
			
			return results;
	}
	
	private float MatchScore(Listing listing, Product product) {
		float relatedWords = 0;
		float totalWords = 0;
		
		String[] titleWords = titleWords(listing);
		ArrayList<String> productWords = productInfoWords(product);
		
		
		totalWords = titleWords.length;
		
		for (String titleWord : titleWords) {
			if (productWords.contains(titleWord)) {
				relatedWords++;
			}
		}
				
		return relatedWords / totalWords;
	}
	
	
	private String[] titleWords(Listing listing) {
		String normalizedTitle = normalize(listing.getTitle());
		String[] titleWords = normalizedTitle.split(" ");
		
		return titleWords;
	}
	
	private ArrayList<String> productInfoWords(Product product) {
		StringBuilder builder = new StringBuilder();
		builder.append(product.getProductName()); 
		builder.append(" ");
		builder.append(product.getManufacturer());
		builder.append(" ");
		builder.append(product.getModel());
		builder.append(" ");
		builder.append(product.getFamily());
		
		String productInfo = builder.toString();
		productInfo = normalize(productInfo);
		
		String[] words = productInfo.split(" ");
		
		
		ArrayList<String> uniqueWords = new ArrayList<String>();
		
		for (String word : words) {
			if (!uniqueWords.contains(word)) {
				uniqueWords.add(word);
			}
		}
		
		return uniqueWords;
	}

	public static String normalize(String input) {
		String normalized = "";

		normalized = input.replaceAll("[/,_-]", " ");

		normalized = normalized.replaceAll("[^a-zA-Z0-9\\ ]", "");

		normalized = normalized.replaceAll(" +", " ");

		return normalized.toLowerCase().trim();
	}
}
