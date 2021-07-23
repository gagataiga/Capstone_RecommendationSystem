
/**
 * Write a description of ThirdRatings here.
 * 
 * @author Taiga Shiga
 * @version (a version number or a date)
 */

import java.util.ArrayList;
import java.util.*;

public class ThirdRatings {
	private ArrayList<Rater> myRaters;
	private HashMap<String,HashMap<String,Rating>>myRatings;
    
    public ThirdRatings() {
        this("data/ratings_short.csv");
    }

	public ThirdRatings(String ratingsfile) {
		FirstRatings firstRatings = new FirstRatings();
		this.myRaters = firstRatings.loadRaters(ratingsfile);
		this.myRatings = firstRatings.loadRatings(ratingsfile);
	}
	
	public ArrayList<Rater> getMyRaters() {
		return this.myRaters;
	}

	public double getAverageById(String id, int minimalRaters) {
        double totalRating = 0;
        double avarage = 0;
        double countRaters = 0;

        for (HashMap<String, Rating> ratingMap : myRatings.values()) {
            for (Rating rating : ratingMap.values()) {
                if (rating.getItem().equals(id)) {
                    double tempRatingNum = rating.getValue();
                    totalRating += tempRatingNum;
                    countRaters += 1;
                }
            }
        }

        if (countRaters < minimalRaters) {
            return 0.0;
        } else {
            avarage = totalRating / countRaters;
        }
        return avarage;
    }


    //an ArrayList of all the Rating objects for movies that have at least the minimal number of raters supplying a rating
	public ArrayList<Rating> getAverageRatings(int minimalRaters) {
		// get all movies ID 
		// movie ID is like "567685"
		ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
		ArrayList<Rating> ratingsList = checkMinimalRaters(minimalRaters, movies);
		return ratingsList;
	}
	
	// In the ThirdRatings class, write a public helper method named getAverageRatingsByFilter that has two parameters, an int named minimalRaters for the minimum number of ratings a movie must have and a Filter named filterCriteria. This method should create and return an ArrayList of type Rating of all the movies that have at least minimalRaters ratings and satisfies the filter criteria. This method will need to create the ArrayList of type String of movie IDs from the MovieDatabase using the filterBy method before calculating those averages. 
	public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria) {
        // get specic movies ID
		ArrayList<String> specficMovies = MovieDatabase.filterBy(filterCriteria);
		ArrayList<Rating> ratingByFilterList = checkMinimalRaters(minimalRaters, specficMovies);
		return ratingByFilterList;
	}


	public ArrayList<Rating> checkMinimalRaters(int minimalRaters, ArrayList<String> movies) {
		ArrayList<Rating> ratingsList = new ArrayList<Rating>();
		for (String movieID : movies) {
			// greater than 0.0 is okey
			double avarageNum = getAverageById(movieID, minimalRaters);
			if (avarageNum != 0.0) {
				Rating rating = new Rating(movieID, avarageNum);
				ratingsList.add(rating);
			}
		}
		return ratingsList;
	}

	

	

}
