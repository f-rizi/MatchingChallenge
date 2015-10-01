MatchingChallenge
================
This is a solution for the [Sortable Coding Challenge] (http://sortable.com/challenge/). In this problem we have a set of products and a set of price listings matching some of those products. The task is to match each listing to the correct product.


Bulding and Running
-------------------
* Clone the repository from https://github.com/femami/MatchingChallenge.git
* Copy listings.txt and product.txt file to the same directory
* To clean previous build do ```ant clean```
* To build do ```ant build```
* To run the application do ```ant run```. After running the application the results.txt file will be created in the root directory of the project.


Running the tests
-----------------
In this application, We have some unit tests for MainClass and Matcher.

* To run all of them do ```ant runTest```
* To run MainClass's tests do ```ant MainClassTest```
* To run Matcher's test do ```ant MathcerTest```


Classes
-------
* **MainClass**. This class reads and parses ```products.txt``` and ```listings.txt```, then uses the ```Matcher``` class to produce the results, and writes the results in ```results.txt```.
* **Product**. Contains data about a product. For each of the json objects in ```products.txt```, we create an instance of this class.
* **Listing**. Contains data about a listing. For each of the json objects in ```listings.txt```, we create an instance of this class.
* **Matcher**. Implements the main algorithm of this application and finds the list of matching listings for each of the products.


The Algorithm
-------------
We define __matchScore(product, listing)__ as follows:

    commonWords = (number of common words between listing's title and product's words)
    totalWords = (total number of words in listings' title)
    matchScore(product, listing) = commonWords / totalWords
    
In the matching algorithm, for each listing we find the product that gives the highest match score. To avoid matching with wrong products, we only consider match scores greater than 0.7.

Since the number of products (n = 700) and the number of listings (m = 20,000) are not too big, an algorithm that compares each listing against all of products, seems to be have an acceptable performance and runs under 3 seconds in MacBook Pro.

More efficient algorithms should be possible, but I tried to find the simplest algorithm that gives the required performance.
