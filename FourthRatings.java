
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


	public double dotProduct(Rater me, Rater r) {
		double sum = 0;
		ArrayList<String> items = r.getItemsRated();
		// using r's itemlist 
		for (String item : items) {
			// check me is inclued item
			if (me.hasRating(item)) {
				// adding
				sum += (me.getRating(item) - 5) * (r.getRating(item) - 5);
			}
		}
		return sum;
	}
	

	private ArrayList<Rating> getSimilarities(String id) {
		ArrayList<Rating> resultRating = new ArrayList<Rating>();
		Rater myRater = RaterDatabase.getRater(id);
		ArrayList<Rater> raters = RaterDatabase.getRaters();
		for (Rater rater : raters) {
			// remove my rater
			if (rater.equals(myRater)) {
				continue;
			}
			double resultNum = dotProduct(myRater, rater);
			if (resultNum < 0) {
				continue;
			}
			resultRating.add(new Rating(rater.getID(), resultNum));
		}
		Collections.sort(resultRating, Collections.reverseOrder());
		return resultRating;
	}
	

	public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters) {
		// for return result
		ArrayList<Rating> ratingsList = new ArrayList<Rating>();
		// private helper 
		ArrayList<Rating> similerRatings = getSimilarities(id);
		ArrayList<String> allMovies = MovieDatabase.filterBy(new TrueFilter());

		for (String movieID : allMovies) {
			double sumWeight = 0.0;
			int totalRaters = 0;
			for (int i = 0; i < numSimilarRaters; i++) {
				// i番目に似ているsimilerRatingを取り出す
				Rating similerRating = similerRatings.get(i);
				// i番目のsimilerRatingのmovieIDを使用して
				// 特定のRaterをもってくる
				Rater r = RaterDatabase.getRater(similerRating.getItem());

				// 引数idが持つ映画が存在した場合はおすすめ対象からはずす
				if (RaterDatabase.getRater(id).hasRating(movieID)) {
					continue;
				}

				// 自分とsimilerなraterが特定のmovieをもっていた場合には
				// sum wegihtに追加していく
				if (r.hasRating(movieID)) {
					sumWeight += (r.getRating(movieID)) * similerRating.getValue();
					totalRaters += 1;
				}
			}

			// もし、minimalRaters よりも あるmovieeo

			if (totalRaters >= minimalRaters) {
				// 
				ratingsList.add(new Rating(movieID, sumWeight/totalRaters));
			}
		}
		// sort
		Collections.sort(ratingsList, Collections.reverseOrder());
		return ratingsList;
	}


	public ArrayList<Rating> getSimilarRatingsByFilter(String id, int numSimilarRaters, int minimalRaters,
			Filter filterCriteria) {
		// result
		ArrayList<Rating> ratingsList = new ArrayList<Rating>();
		// private helper
		ArrayList<Rating> similerRatings = getSimilarities(id);
		// specific filter from arg
		ArrayList<String> allMovies = MovieDatabase.filterBy(filterCriteria);


		for (String movieID : allMovies) {
			double sumWeight = 0.0;
			int totalRaters = 0;
			for (int i = 0; i < numSimilarRaters; i++) {
				// i番目に似ているsimilerRatingを取り出す
				Rating similerRating = similerRatings.get(i);
				// i番目のsimilerRatingのmovieIDを使用して
				// 特定のRaterをもってくる
				Rater r = RaterDatabase.getRater(similerRating.getItem());

				// 引数idが持つ映画が存在した場合はおすすめ対象からはずす
				// if (RaterDatabase.getRater(id).hasRating(movieID)) {
				// 	continue;
				// }

				// 自分とsimilerなraterが特定のmovieをもっていた場合には
				// sum wegihtに追加していく
				if (r.hasRating(movieID)) {
					sumWeight += (r.getRating(movieID)) * similerRating.getValue();
					totalRaters += 1;
				}
			}

			// もし、minimalRaters よりも あるmovieeo

			if (totalRaters >= minimalRaters) {
				ratingsList.add(new Rating(movieID, sumWeight/totalRaters));
			}
		}
		// sort
		Collections.sort(ratingsList, Collections.reverseOrder());
		return ratingsList;
	}
}
