import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import com.apple.laf.AquaButtonRadioUI.RadioButtonBorder;

/**
 * Write a description of RecommendationRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RecommendationRunner implements Recommender{
	@Override
	public ArrayList<String> getItemsToRate() {
		// TODO Auto-generated method stub
		ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
		ArrayList<String> movieListforUser = new ArrayList<String>();
		for (String movie : movies) {
			// Random select
			Random rnd = new Random();
			int randomNum = rnd.nextInt(15);
			int movieId = Integer.parseInt(movie);

			if (movieId % randomNum == 0) {
				movieListforUser.add(movie);
			}
			// Max number of movies to introduce to users
			if (movieListforUser.size() > 10) {
				break;
			}
		}
		return movieListforUser;
	}

	private void show(ArrayList<Rating> recomedsMovies) {
		System.out.println("<h2> Recommendation for you </h2>");
		System.out.println("");
		for (int i = 0; i < 10; i++) {
			System.out.println("<h3>"+ "No" + (i+1)  + " : " +MovieDatabase.getTitle(recomedsMovies.get(i).getItem()) + "</3>");
		}
	}	

	@Override
	public void printRecommendationsFor(String webRaterID) {
		FourthRatings fourthRatings = new FourthRatings();
		AllFilters allFilters = new AllFilters();
		String[] genreList = { "Comedy", "Dorama", "Horror", "Adventure", "Action", "Music", "Mystery" };
		
		Random rnd = new Random();
		int randomNum = rnd.nextInt(7);
		allFilters.addFilter(new GenreFilter(genreList[randomNum]));
		allFilters.addFilter(new MinutesFilter(40, 250));
		ArrayList<Rating> movies = fourthRatings.getSimilarRatingsByFilter(webRaterID, 10, 2, allFilters);

		// if we can't find recomended movies for user
		// applogize to them
		if (movies.size() == 0) {
			System.out.println("<p></p>");
		} else {
			// finally we can show users our recommenditon movies 
			show(movies);
		}
	}
	

}
