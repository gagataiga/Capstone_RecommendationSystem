
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
    
	public HashMap<String, Integer> directorAndMovie(ArrayList<Movie> movies) {
		HashMap<String, Integer> directoresMap = new HashMap<String, Integer>();
		for (Movie movie : movies) {
			String director = movie.getDirector();
			// directores doesn't contain "," 
			// and directoresMap doesn't contain the directore name
			if (!director.contains(",") && !directoresMap.containsKey(director)){
				// put
				directoresMap.put(director, 1);
		    // directores doesn't contain ","
			// but directoresMap contains dire
			} else if (!director.contains(",")) {
			// increment key value
				directoresMap.put(director, directoresMap.get(director) + 1);
			}
			
			// director contains ","
			if (director.contains(",")) {
				String regex = ",";
				// split with ",""
				String[] data = director.split(regex);
				// "str = directore name"
				for (String key : data) {
					if (directoresMap.containsKey(key)) {
						directoresMap.put(key, directoresMap.get(key) + 1);
					} else {
						directoresMap.put(key, 1);
					}
				}
			}
			
		}

		return directoresMap;
	}

	public void testLoadMovies() {
		// get movielist 
		ArrayList<Movie> movielist = LoadMovies("ratedmoviesfull.csv");
		// conform the number of movielist
		// ratedmoviesfull.csv -> 3142 ok	
		 //for (Movie movie : movielist) {
		// 	System.out.println(movie.getDirector());
		 //}
		int countComedies = 0;
		int gt150minMovies = 0;
		// Movie comdyMovie;
		for (Movie movie : movielist) {
			// 1 Add code to determine how many movies include the Comedy genre. 
			// In the file ratedmovies_short.csv, there is only one.
			if (movie.getGenres().contains("Comedy")) {
				countComedies += 1;
			}
			// Add code to determine how many movies are greater than 150 minutes in length. 
			// In the file ratedmovies_short.csv, there are two. 
			if (movie.getMinutes() > 150) {
				gt150minMovies += 1;
			}
		}
		System.out.println(countComedies);
		System.out.println(gt150minMovies);

		// 2 Add code to determine the maximum number of movies by any director, and who the directors are that directed that many movies
		HashMap<String, Integer> directorAndMovieMap = directorAndMovie(movielist);

		int maximum = 0;
		String director = "";
		for (String key : directorAndMovieMap.keySet()) {
			// System.out.println(key + ":" + directorAndMovieMap.get(key));
			if (maximum == 0) {
				maximum = directorAndMovieMap.get(key);
				director = key;
			} else if (maximum < directorAndMovieMap.get(key)) {
				maximum = directorAndMovieMap.get(key);
				director = key;
			}
		}

		ArrayList<String> maxdirectores = new ArrayList<String>();
		for (String key : directorAndMovieMap.keySet()) {
			if (directorAndMovieMap.get(key) == maximum) {
				maxdirectores.add(key);
			}	
		}

		System.out.println("the maximum number of movies by any director" + maximum);
		System.out.println("who the directors are that directed that many movies ");
		for (String s : maxdirectores){
			System.out.println(s);
		}
	}
	


	public ArrayList<Rater> loadRaters(String fileName){
		ArrayList<Rater> rater = new ArrayList<Rater>();

		return rater;
	}
}
