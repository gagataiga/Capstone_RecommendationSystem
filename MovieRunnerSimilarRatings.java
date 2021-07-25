
/**
 * Write a description of MovieRunnerSimilarRatings here.
 * 
 * @author Taiga Shiga
 * @version (a version number or a date)
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class MovieRunnerSimilarRatings {

	public void printAverageRatings() {
        FourthRatings fourthRatings = new FourthRatings();
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
		// // conform 
		// for (Rater rater : fourthRatings.getMyRater()) {
		// 	// System.out.print(rater.getMyRating().size());
		// 	// System.out.print(rater.getMyRating().values());
		// 	for (Rating a : rater.getMyRating().values()) {
		// 		System.out.println(a);
		// 		System.out.println("----");
		// 	}
		// 	System.out.println("");
		// }
        System.out.println("read data for " + fourthRatings.getMyRater().size() + " raters");
        System.out.println("read data for " + movies.size() + " movies");

        ArrayList<Rating> ratingList = fourthRatings.getAverageRatings(35);
        System.out.println("found " + ratingList.size() + " movies");
        // bubblie sort
        Sort sort = new Sort(ratingList);
        sort.ascendingOrder();

        for (Rating rating : sort.getRatingList()) {
            String id = rating.getItem();
            System.out.println("rating: " + rating.getValue() + " movie: " + MovieDatabase.getTitle(id));
        }
    }
    
    public void printAverageRatingsByYearAfterAndGenre() {

        FourthRatings fourthRatings = new FourthRatings();
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        // conform 
        System.out.println("read data for " + fourthRatings.getMyRater().size() + " raters");
        System.out.println("read data for " + movies.size() + " movies");

        AllFilters allFilters = new AllFilters();
        int year = 1990;
        String genre = "Drama";
        allFilters.addFilter(new YearAfterFilter(year));
        allFilters.addFilter(new GenreFilter(genre));

        ArrayList<Rating> ratingsList = fourthRatings.getAverageRatingsByFilter(8, allFilters);

        System.out.println("found " + ratingsList.size() + " movies");

        Sort sort = new Sort(ratingsList);
        sort.ascendingOrder();

        for (Rating rating : sort.getRatingList()) {
            String id = rating.getItem();
            System.out.println("rating: " + rating.getValue() + " Year:" + MovieDatabase.getYear(id) + " movie: "
                    + MovieDatabase.getTitle(id) + " Genre:" + MovieDatabase.getGenres(id));
        }
    }
    
	public void test() {
	
	
	
	}

   

}
