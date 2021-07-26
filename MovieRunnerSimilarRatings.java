
/**
 * Write a description of MovieRunnerSimilarRatings here.
 * 
 * @author Taiga Shiga
 * @version (a version number or a date)
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import javax.swing.plaf.basic.BasicSliderUI.ScrollListener;



public class MovieRunnerSimilarRatings {

    public void printAverageRatings() {
        FourthRatings fourthRatings = new FourthRatings();
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        // // conform 
        // for (Rater rater : fourthRatings.getMyRater()) {
        //  // System.out.print(rater.getMyRating().size());
        //  // System.out.print(rater.getMyRating().values());
        //  for (Rating a : rater.getMyRating().values()) {
        //      System.out.println(a);
        //      System.out.println("----");
        //  }
        //  System.out.println("");
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
    
    public void printSimilarRatings() {
        FourthRatings fourthRatings = new FourthRatings();
        String raterID = "71";
        int similarNum = 20;
        int min = 5;
        ArrayList<Rating> similarRatings = fourthRatings.getSimilarRatings(raterID, similarNum, min);
        for (Rating rating : similarRatings) {
            System.out.println(MovieDatabase.getTitle(rating.getItem()));
        }
    }
    
    public void printSimilarRatingsByGenre() {
        FourthRatings fourthRatings = new FourthRatings();
        String raterID = "964";
        int similarNum = 20;
        int min = 5;
        GenreFilter genreFilter = new GenreFilter("Mystery");
        ArrayList<Rating> similarRatings = fourthRatings.getSimilarRatingsByFilter(raterID, similarNum, min,
                genreFilter);
        for (Rating rating : similarRatings) {
            System.out.println(MovieDatabase.getTitle(rating.getItem()));
        }
    }

    public void printSimilarRatingsByDirector() {
        FourthRatings fourthRatings = new FourthRatings();
        String raterID = "120";
        int similarNum = 10;
        int min = 2;
        String direName = "Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh";
        DirectorsFilter directorsFilter = new DirectorsFilter(direName);
        ArrayList<Rating> similarRatings = fourthRatings.getSimilarRatingsByFilter(raterID, similarNum, min,directorsFilter
        );
        for (Rating rating : similarRatings) {
        System.out.println(MovieDatabase.getTitle(rating.getItem()));
        }
    }
	
    public void printSimilarRatingsByGenreAndMinutes() {
        String raterID = "168";
        int similarNum = 10;
        int min = 3;
        String genre = "Adventure";
        int minMinutes = 80;
        int maxMinutes = 160;
        AllFilters allFilters = new AllFilters();
        allFilters.addFilter(new GenreFilter(genre));
        allFilters.addFilter(new MinutesFilter(minMinutes, maxMinutes));
        MovieDatabase.initialize("ratedmoviesfull.csv");
        FourthRatings fourthRatings = new FourthRatings();
        ArrayList<Rating> similarRatings = fourthRatings.getSimilarRatingsByFilter(raterID, similarNum, min,allFilters);
        for (Rating rating : similarRatings) {
        System.out.println(MovieDatabase.getTitle(rating.getItem()));
        }
    }
    
    
    public void printSimilarRatingsByYearAfterAndMinutes() {
        String raterID = "314";
        int similarNum = 10;
        int min = 5;
        int year = 1975;
        int minMinutes = 70;
        int maxMinutes = 200;
        AllFilters allFilters = new AllFilters();
        allFilters.addFilter(new YearAfterFilter(year));
        allFilters.addFilter(new MinutesFilter(minMinutes, maxMinutes));
        FourthRatings fourthRatings = new FourthRatings();
        ArrayList<Rating> similarRatings = fourthRatings.getSimilarRatingsByFilter(raterID, similarNum, min,allFilters
        );
        for (Rating rating : similarRatings) {
        System.out.println(MovieDatabase.getTitle(rating.getItem()));
        }
    }


	public void test1() {
		FourthRatings fourthRatings = new FourthRatings();
		for (Rater rater : fourthRatings.getMyRater()) {
		        System.out.println(rater.getItemsRated());
			}
		}

   

}
