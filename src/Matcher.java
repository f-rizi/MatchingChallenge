import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public final class Matcher {

	private static String[] PREPOSITIONS = { "for", "für" };
	private static String[] POSTPOSITIONS = { "only" };
	private static String[] CONJUCTIONS = { "and", "+", "with" };

	private Matcher() {
	}

	public static HashMap<String, ArrayList<Listing>> getResults2(
			ArrayList<Product> products, ArrayList<Listing> listings) {

		HashMap<String, ArrayList<Listing>> results2 = new HashMap<String, ArrayList<Listing>>();

		for (Listing listing : listings) {
			ArrayList<Product> producCandidate = new ArrayList<Product>();

			System.out.println(listing.getTitle());

			for (Product product : products) {
				if (listingMachesProduct(product, listing)) {
					producCandidate.add(product);
				}
			}

			if (producCandidate.size() > 0) {
				Product bestProduct;

				if (producCandidate.size() > 1) {
					bestProduct = getBestCandidate(producCandidate, listing);

				} else {
					bestProduct = producCandidate.get(0);
				}

				ArrayList<Listing> machedListings;
				if (results2.containsKey(bestProduct.getProductName())) {
					machedListings = results2.get(bestProduct.getProductName());

				} else {
					machedListings = new ArrayList<Listing>();
				}

				machedListings.add(listing);

				results2.put(bestProduct.getProductName(), machedListings);

			}
		}

		return results2;

	}

	private static Product getBestCandidate(ArrayList<Product> producCandidate,
			Listing listing) {
		Product bestCandidate = producCandidate.get(0);

		int maxNotEmptyItems = 0;

		for (Product product : producCandidate) {
			String[] fields = { product.getProductName(),
					product.getProductName(), product.getManufacturer() };

			int notEmptyItems = 0;

			for (String field : fields) {
				if (!field.equals("")) {
					notEmptyItems++;
				}
			}

			if (notEmptyItems > maxNotEmptyItems) {
				bestCandidate = product;
			}
		}

		return bestCandidate;
	}

	// public static ArrayList<Result> getResults(ArrayList<Product> products,
	// ArrayList<Listing> listings) {
	// ArrayList<Result> results = new ArrayList<Result>();
	//
	// for (Product product : products) {
	// ArrayList<Listing> machedListings = new ArrayList<Listing>();
	//
	// for (Listing listing : listings) {
	// if (doseMatch(product, listing)) {
	// machedListings.add(listing);
	// }
	// }
	//
	// if (machedListings.size() > 0) {
	// Result result = new Result();
	// result.setProductName(product.getProductName());
	// result.setListings(machedListings);
	//
	// results.add(result);
	// }
	// }
	//
	// return results;
	// }

	// static boolean doseMatch(Product product, Listing listing) {
	// String productName = normalize(product.getProductName());
	// String listingTitle = normalize(listing.getTitle());
	//
	// int nameIndex = listingTitle.indexOf(productName);
	//
	// int prepositionIndex = -1;
	// int postpositionIndex = -1;
	//
	// for (String preposition : PREPOSITIONS) {
	// if (listingTitle.contains(preposition)) {
	// prepositionIndex = listingTitle.indexOf(preposition);
	// }
	// }
	//
	// for (String postposition : POSTPOSITIONS) {
	// if (listingTitle.contains(postposition)) {
	// postpositionIndex = listingTitle.indexOf(postposition);
	// }
	// }
	//
	// if (nameIndex > -1) {
	//
	// if (prepositionIndex != -1 && prepositionIndex < nameIndex) {
	// return false;
	// }
	//
	// if (postpositionIndex != -1 && postpositionIndex > nameIndex) {
	// return false;
	// }
	//
	// return true;
	// }
	//
	// return false;
	// }

	private static boolean listingMachesProduct(Product product, Listing listing) {

		if (manufacturersMach(product, listing)) {
			int productInfoIndex = productInfoIndexInTitle(product, listing);
			int prepositionIndex = prepositionIndex(listing);
			int postpositionIndex = postpositionIndex(listing);

			// System.out.println("productInfoIndex : " + productInfoIndex);
			// System.out.println("prepositionIndex : " + prepositionIndex);
			// System.out.println("postpositionIndex : " + postpositionIndex);

			if (productInfoIndex > -1) {

				if (prepositionIndex != -1
						&& prepositionIndex < productInfoIndex) {
					// System.out.println("preposition error");
					return false;
				}

				if (postpositionIndex != -1
						&& postpositionIndex > productInfoIndex) {
					// System.out.println("postposition error");
					return false;
				}

				// System.out.println("titleHasPpoduct");
				return true;
			}
		}

		return false;
	}

	private static boolean manufacturersMach(Product product, Listing listing) {
		String productManufacturer = normalize(product.getManufacturer());
		String listingManufacturer = normalize(listing.getManufacturer());

		if (productManufacturer.equals(listingManufacturer)) {
			// System.out.println("1 : manufactors OK");
			return true;
		}

		if (productManufacturer.length() < listingManufacturer.length()) {
			if (listingManufacturer.contains(productManufacturer)) {
				// System.out.println("2 : manufactors OK");
				return true;
			}
		}

		if (productManufacturer.length() > listingManufacturer.length()) {
			if (productManufacturer.contains(listingManufacturer)) {
				// System.out.println("3 : manufactors OK");
				return true;
			}
		}

		// System.out.println("manufactors not mach");
		return false;
	}

	private static int productInfoIndexInTitle(Product product, Listing listing) {

		String listingTitle = normalize(listing.getTitle());

		String model = normalize(product.getModel());
		String family = normalize(product.getFamily());
		String manufactuter = normalize(product.getManufacturer());

		// System.out.println("productFamily :" + productFamily);
		// System.out.println("productModel :" + productModel);

		String[] permutations = new String[6];

		permutations[0] = getStringFrom(model, family, manufactuter);
		permutations[1] = getStringFrom(model, manufactuter, family);
		permutations[2] = getStringFrom(family, model, manufactuter);
		permutations[3] = getStringFrom(family, manufactuter, model);
		permutations[4] = getStringFrom(manufactuter, family, model);
		permutations[5] = getStringFrom(manufactuter, model, family);

		int productInfoIndex = -1;

		// System.out.println("listingTitle :" + listingTitle);

		for (String permitation : permutations) {
			productInfoIndex = -1;

			if (listingTitle.contains(permitation)) {
				int index = acceptableSubstring(listingTitle, permitation);
				if (index > -1) {
					// System.out.println("permutation is :" + permitation);
					return index;
				}
			}
		}

		// System.out.println("last productInfoIndex : " + productInfoIndex);

		return -1;
	}

	private static int acceptableSubstring(String mainString, String subString) {
		int index = mainString.indexOf(subString);

		// System.out.println("permutation is :" + subString + ", index :" +
		// index);

		// System.out.println("before"+ listingTitle.charAt(index - 1));
		if (index > 0 && mainString.charAt(index - 1) != ' ') {
			// System.out.println("error 1");
			return -1;
		}

		int nextCharIndex = index + subString.length();
		// System.out.println("after"+
		// listingTitle.charAt(nextCharIndex));
		if (nextCharIndex < mainString.length()
				&& mainString.charAt(nextCharIndex) != ' ') {
			// System.out.println("error 2");
			return -1;
		}

		if (index > -1) {
			// System.out.println("hoooooy------------------------------");
			// productInfoIndex = index;
		}
		return index;
	}

	private static int prepositionIndex(Listing listing) {
		String listingTitle = normalize(listing.getTitle());

		int index = -1;

		for (String preposition : PREPOSITIONS) {
			if (listingTitle.contains(preposition)) {
				index = listingTitle.indexOf(preposition);
			}
		}

		return index;
	}

	private static int postpositionIndex(Listing listing) {
		String listingTitle = normalize(listing.getTitle());

		int index = -1;

		for (String postposition : POSTPOSITIONS) {
			if (listingTitle.contains(postposition)) {
				index = listingTitle.indexOf(postposition);
			}
		}

		return index;
	}

	private static String getStringFrom(String s1, String s2, String s3) {
		StringBuilder builder = new StringBuilder();

		builder.append(s1);
		builder.append(" ");
		builder.append(s2);
		builder.append(" ");
		builder.append(s3);

		return normalize(builder.toString());
	}

	public static String normalize(String input) {
		String normalized = "";

		normalized = input.replaceAll("[/,_-]", " ");

		normalized = normalized.replaceAll("[^a-zA-Z0-9\\ ]", "");

		normalized = normalized.replaceAll(" +", " ");

		return normalized.toLowerCase().trim();
	}
}
