import java.util.ArrayList;
import java.util.Random;

/**
 * Write a description of RecommendationRunner here.
 * 
 * @author Taiga Shiga 
 * @version (a version number or a date)
 */
public class RecommendationRunner implements Recommender{
	@Override
	public ArrayList<String> getItemsToRate() {
		ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
		ArrayList<String> movieListforUser = new ArrayList<String>();

		for (int i = 0; i < 15; i++) {
			movieListforUser.add(movies.get(i));
		}
		
		return movieListforUser;
	}

	private void show(ArrayList<Rating> movies) {
		System.out.println("<h2> Recommendation for you </h2>");
		System.out.println("");
		int movieNum = movies.size();
		int num = 10;

		// もしmovieNum が10より少ない場合は入れ替える
		if (movieNum < num) {
			num = movieNum;
		}
		System.out
				.println("<style>p{ color: green;" + "\n" + "font-style: italic;" + "\n" + "font-size: 20px;}</style>");

		for (int i = 0; i < num; i++) {
			System.out.println(
					"<p>" + "No" + (i + 1) + " " + 
							"<img src=" + "\"" + MovieDatabase.getPoster(movies.get(i).getItem()) + "\""
							+ "alt=" + "\"" +"movie_img"+  "\"" 
							+ "width=" 
							+ "\"" + 50 + "\"" + "height=" + "\"" + 50 + "\"" + ">"
					+ " : " + MovieDatabase.getTitle(movies.get(i).getItem()) 
							+ "<p>");			
		}
		
	}	

	@Override
	public void printRecommendationsFor(String webRaterID) {
		FourthRatings fourthRatings = new FourthRatings();
		// AllFilters allFilters = new AllFilters();
		// String[] genreList = { "Comedy", "Dorama", "Horror", "Adventure", "Action", "Music", "Mystery" };
		
		// Random rnd = new Random();
		// int randomNum = rnd.nextInt(7);
		// allFilters.addFilter(new GenreFilter(genreList[randomNum]));
		// allFilters.addFilter(new MinutesFilter(40, 250));
		ArrayList<Rating> movies = fourthRatings.getSimilarRatingsByFilter(webRaterID, 10, 2, new TrueFilter());

		// if we can't find recomended movies for user
		// applogize to them
		System.out.println("<style>p{ color: green;}</style>");
		if (movies.size() == 0) {
			System.out.println("<p>Sorry we can't find any recommendations</p>");
		} else {
			// finally we can show users our recommenditon movies 
			show(movies);
		}
	}
	
	
}
