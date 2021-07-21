
/**
 * Write a description of FirstRatings here.
 * 
 * @author Taiga shiga
 * 
 */

import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class FirstRatings {

    public ArrayList<Movie> LoadMovies(String filename) {
        ArrayList<Movie> movieList = new ArrayList<Movie>();
        FileResource fr = new FileResource("data/" + filename);
        CSVParser parser = fr.getCSVParser();
        
        for(CSVRecord record:parser){
            Movie movie = new Movie(record.get("id"),record.get("title"),record.get("year"),record.get("genre"),
            record.get("director"),record.get("country"),record.get("poster"),Integer.parseInt(record.get("minutes")));
            movieList.add(movie);
        }
        return movieList;
    }
    
	public void testLoadMovies() {
		// get movielist 
		ArrayList<Movie> movielist = LoadMovies("ratedmoviesfull.csv");
		// conform the number of movielist
		// ratedmoviesfull.csv -> 3142 ok	
		for (Movie movie : movielist) {
			System.out.println(movie.getDirector());
		}
		int countComedies = 0;
		int lt150minMovies = 0;
		// Movie comdyMovie;
		for (Movie movie : movielist) {
			// 1 Add code to determine how many movies include the Comedy genre. 
			// In the file ratedmovies_short.csv, there is only one.
			if (movie.getGenres().contains("Comedy")) {
				countComedies += 1;
			}
			// Add code to determine how many movies are greater than 150 minutes in length. 
			// In the file ratedmovies_short.csv, there are two. 
			if (movie.getMinutes() < 150) {
				lt150minMovies += 1;
			}
		}
		System.out.println(countComedies);
		System.out.println(lt150minMovies);

		// 2 Add code to determine the maximum number of movies by any director, and who the directors are that directed that many movies
		
	}
	


	public ArrayList<Rater> loadRaters(String fileName){
		ArrayList<Rater> rater = new ArrayList<Rater>();

		return rater;
	}
}
