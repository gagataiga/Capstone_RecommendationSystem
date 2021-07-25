
/**
 *
 * @author Taiga Shiga
 * @version (a version number or a date)
 */

import java.util.ArrayList;
import java.util.*;

public class FourthRatings {

	public FourthRatings(){
		this("data/ratings.csv");
	}

	public FourthRatings(String fileName) {
		RaterDatabase.addRatings(fileName);
	}

	public ArrayList<Rater> getMyRater(){
		return RaterDatabase.getRaters();
	}
	
	
	public double getAverageById(String id, int minimalRaters) {
		ArrayList<Rater> raters = RaterDatabase.getRaters();
		double totalRating = 0;
		double avarage = 0;
		double countRaters = 0;

		// for (HashMap<String, Rating> ratingMap : myRatings.values()) {
		//     for (Rating rating : ratingMap.values()) {
		//         if (rating.getItem().equals(id)) {
		//             double tempRatingNum = rating.getValue();
		//             totalRating += tempRatingNum;
		//             countRaters += 1;
		//         }
		//     }
		// }
		
		for (Rater rater : raters) {
			HashMap<String, Rating> Ratings = rater.getMyRating();
			for (Rating rating : Ratings.values()) {
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

	// - Write the private helper method named dotProduct, which has two parameters, a Rater named me and a Rater named r. 
	// This method should first translate a rating from the scale 0 to 10 to the scale -5 to 5 and return the dot product of the ratings of movies that they both rated. This method will be called by getSimilarities.

	public double dotProduct(Rater me, Rater r) {
		HashMap<String, Rating> myRatingMap = me.getMyRating();
		HashMap<String, Rating> rRatingMap = r.getMyRating();
		double result = 0.0;

		// 1:add Ratings to myMoviesRat
		ArrayList<Rating> myMoviesRat = new ArrayList<Rating>();
		for (Rating myRat : myRatingMap.values()) {
			myMoviesRat.add(myRat);
		}

		// 2: add Rating to rMoviesRat;
		ArrayList<Rating> rMoviesRat = new ArrayList<Rating>();
		for (Rating rRat : rRatingMap.values()) {
			rMoviesRat.add(rRat);
		}

		// 3: if r's movieID contains mymovie'ID
		// then it can calculate and get result;
		for (int i = 0; i < myMoviesRat.size(); i++) {
			String myMovieID = myMoviesRat.get(i).getItem();
			Double myValue = myMoviesRat.get(i).getValue();

			for (int k = 0; k < rMoviesRat.size(); k++) {
				String rMovieID = rMoviesRat.get(k).getItem();
				double rValue = rMoviesRat.get(k).getValue();

				// check if myMovieID is inculded rMovieID
				if (myMovieID.contains(rMovieID)) {
					double tmpValue = 0.0;
					tmpValue = (rValue - 5) * (myValue - 5);
					// adding
					result += tmpValue;
				}
			}
		}
        return result;
    }
	

	// This method returns an ArrayList of type Rating sorted by ratings from highest to lowest rating with the highest rating first and only including those raters who have a positive similarity rating since those with negative values are not similar in any way
	private ArrayList<Rating> getSimilarities(String id) {
		ArrayList<Rating> resultRating = new ArrayList<Rating>();
		Rater myRater = RaterDatabase.getRater(id);

		for (Rater rater : RaterDatabase.getRaters()) {
			if (!rater.getID().equals(id)) {
				Double resultNum = dotProduct(myRater, rater);
				if (resultNum > 0) {
					resultRating.add(new Rating(rater.getID(), resultNum));
				}
			}
		}
		Collections.sort(resultRating, Collections.reverseOrder());
		return resultRating;
	}
	

	//This method should return an ArrayList of type Rating, of movies and their weighted average ratings using only the top numSimilarRaters with positive ratings and including only those movies that have at least minimalRaters ratings from those most similar raters (not just minimalRaters ratings overall). 
	public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters){
		ArrayList<Rating> ratingsList = new ArrayList<Rating>();
		ArrayList<Rating> similerRatings = getSimilarities(id);
		ArrayList<String> allMovies = MovieDatabase.filterBy(new TrueFilter());
		double ratingNum = 0;

		for(){
		}
		
		return similerRatings;
	}

}
