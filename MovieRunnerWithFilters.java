
/**
 * Write a description of MovieRunnerWithFilters here.
 * 
 * @author Taiga Shiga
 * @version (a version number or a date)
 */

import java.util.ArrayList;
import java.util.Collections;

public class MovieRunnerWithFilters {



	public void printAverageRatings() {
		ThirdRatings thirdRatings = new ThirdRatings();
		ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
		// conform 
		System.out.println("read data for " + thirdRatings.getMyRaters().size() + " raters");
		System.out.println("read data for " + movies.size() + " movies");

		ArrayList<Rating> ratingList = thirdRatings.getAverageRatings(1);
		System.out.println("found " + ratingList.size() + " movies");
		// bubblie sort
		Sort sort = new Sort(ratingList);
		sort.ascendingOrder();

		for (Rating rating : sort.getRatingList()) {
			String id = rating.getItem();
			System.out.println("rating: "+ rating.getValue() + " movie: " + MovieDatabase.getTitle(id));
		}
	}

	public void printAverageRatingsByYear() {
		ThirdRatings thirdRatings = new ThirdRatings();
		ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
			// conform 
			System.out.println("read data for " + thirdRatings.getMyRaters().size() + " raters");
			System.out.println("read data for " + movies.size() + " movies");

		int year = 2000;
		ArrayList<Rating> ratingYearList = thirdRatings.getAverageRatingsByFilter(1, new YearAfterFilter(year));
		Sort sort = new Sort(ratingYearList);
		sort.ascendingOrder();
		
		for (Rating rating : sort.getRatingList()) {
			String id = rating.getItem();
			System.out.println("rating:"+ rating.getValue() + " year:" + MovieDatabase.getYear(id) +  " movie:" + MovieDatabase.getTitle(id));
		}
	}



	}
