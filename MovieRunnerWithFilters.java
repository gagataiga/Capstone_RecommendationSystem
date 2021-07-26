
/**
 * Write a description of MovieRunnerWithFilters here.
 * 
 * @author Taiga Shiga
 * @version (a version number or a date)
 */

import java.util.ArrayList;
import java.util.Collections;

import javax.swing.text.Style;

public class MovieRunnerWithFilters {

	public void printAverageRatings() {
		ThirdRatings thirdRatings = new ThirdRatings();
		ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
		// conform 
		System.out.println("read data for " + thirdRatings.getMyRaters().size() + " raters");
		System.out.println("read data for " + movies.size() + " movies");

		ArrayList<Rating> ratingList = thirdRatings.getAverageRatings(35);
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
		ArrayList<Rating> ratingYearList = thirdRatings.getAverageRatingsByFilter(20, new YearAfterFilter(year));
		System.out.println("found " + ratingYearList.size() + " movies");
		Sort sort = new Sort(ratingYearList);
		sort.ascendingOrder();
		
		for (Rating rating : sort.getRatingList()) {
			String id = rating.getItem();
			System.out.println("rating:"+ rating.getValue() + " year:" + MovieDatabase.getYear(id) +  " movie:" + MovieDatabase.getTitle(id));
		}
	}

	public void printAverageRatingsByGenre() {
		ThirdRatings thirdRatings = new ThirdRatings();
		ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());

		// conform
		System.out.println("read data for " + thirdRatings.getMyRaters().size() + " raters");
		System.out.println("read data for " + movies.size() + " movies");
		// genre -> 
		ArrayList<Rating> ratingList = thirdRatings.getAverageRatingsByFilter(20, new GenreFilter("Comedy"));
		System.out.println("found " + ratingList.size() + " movies");

		// bubblie sort
		Sort sort = new Sort(ratingList);
		sort.ascendingOrder();

		for (Rating rating : sort.getRatingList()) {
			String id = rating.getItem();
			System.out.println("rating: " + rating.getValue() + " movie: " + MovieDatabase.getTitle(id) + " Genre: "
					+ MovieDatabase.getGenres(id));
		}
	}
	
	public void printAverageRatingsByMinutes() {
		ThirdRatings thirdRatings = new ThirdRatings();
		ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
		// conform 
		System.out.println("read data for " + thirdRatings.getMyRaters().size() + " raters");
		System.out.println("read data for " + movies.size() + " movies");
		int min = 105;
		int max = 135;
		ArrayList<Rating> ratingList = thirdRatings.getAverageRatingsByFilter(15, new MinutesFilter(min, max));
		System.out.println("found " + ratingList.size() + " movies");

		Sort sort = new Sort(ratingList);
		sort.ascendingOrder();

		for (Rating rating : sort.getRatingList()) {
			String id = rating.getItem();
			System.out.println("rating: " + rating.getValue() + " movie: " + MovieDatabase.getTitle(id) + " Time: "
					+ MovieDatabase.getMinutes(id));
		}
	}

	public void printAverageRatingsByDirectors() {
		ThirdRatings thirdRatings = new ThirdRatings();
		ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
		// conform 
		System.out.println("read data for " + thirdRatings.getMyRaters().size() + " raters");
		System.out.println("read data for " + movies.size() + " movies");
		String directors = "Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack";
		ArrayList<Rating> ratingList = thirdRatings.getAverageRatingsByFilter(4, new DirectorsFilter(directors));

		System.out.println("found " + ratingList.size() + " movies");

		Sort sort = new Sort(ratingList);
		sort.ascendingOrder();

		for (Rating rating : sort.getRatingList()) {
			String id = rating.getItem();
			System.out.println("rating: " + rating.getValue() + " movie: " + MovieDatabase.getTitle(id) + " Directors: "
					+ MovieDatabase.getDirector(id));
		}
	}

	public void printAverageRatingsByYearAfterAndGenre() {

		ThirdRatings thirdRatings = new ThirdRatings();
		ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
		// conform 
		System.out.println("read data for " + thirdRatings.getMyRaters().size() + " raters");
		System.out.println("read data for " + movies.size() + " movies");

		AllFilters allFilters = new AllFilters();
		int year = 1990;
		String genre = "Drama";
		allFilters.addFilter(new YearAfterFilter(year));
		allFilters.addFilter(new GenreFilter(genre));

		ArrayList<Rating> ratingsList = thirdRatings.getAverageRatingsByFilter(8, allFilters);

		System.out.println("found " + ratingsList.size() + " movies");
		
		Sort sort = new Sort(ratingsList);
		sort.ascendingOrder();

		for (Rating rating : sort.getRatingList()) {
			String id = rating.getItem();
			System.out.println("rating: " + rating.getValue() + " Year:" + MovieDatabase.getYear(id) + " movie: "
					+ MovieDatabase.getTitle(id) + " Genre:" + MovieDatabase.getGenres(id));
		}

	}
	
	public void printAverageRatingsByDirectorsAndMinutes() {

		ThirdRatings thirdRatings = new ThirdRatings();
		ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
		// conform 
		System.out.println("read data for " + thirdRatings.getMyRaters().size() + " raters");
		System.out.println("read data for " + movies.size() + " movies");
		AllFilters allFilters = new AllFilters();
		String directors = "Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack";
		int min = 90;
		int max = 180;
		allFilters.addFilter(new DirectorsFilter(directors));
		allFilters.addFilter(new MinutesFilter(min, max));

		ArrayList<Rating> ratingsList = thirdRatings.getAverageRatingsByFilter(3, allFilters);

		System.out.println("found " + ratingsList.size() + " movies");

		Sort sort = new Sort(ratingsList);
		sort.ascendingOrder();

		for (Rating rating : sort.getRatingList()) {
			String id = rating.getItem();
			System.out.println("rating: " + rating.getValue() + " Minutes:" + MovieDatabase.getMinutes(id) + " movie: "
					+ MovieDatabase.getTitle(id) + " Directors:" + MovieDatabase.getDirector(id));
		}
	}
	
	}

