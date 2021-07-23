
/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.lang.reflect.Array;
import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    private HashMap<String,HashMap<String,Rating>> myRatings;
    
    public SecondRatings(String movieFile,String ratingFile) {
        FirstRatings firstRatings = new FirstRatings();
        this.myMovies = firstRatings.loadMovies(movieFile);
        this.myRaters = firstRatings.loadRaters(ratingFile);
    }

    public SecondRatings() {
        this("ratedmoviesfull.csv", "ratings.csv");
    }

    // return the number of raters that were read in and stored in the ArrayList of type Rater
    public int getRaterSize() {
        return this.myRaters.size();
    }

     // return the number of movies that were read in and stored in the ArrayList of type Movie
    public int getMovieSize() {
        return this.myMovies.size();
    }


    // This method returns a double representing the average movie rating for this ID if there are at least minimalRaters ratings. If there are not minimalRaters ratings, then it returns 0.0.
    public double getAverageById(String id, int minimalRaters) {
        double totalRating = 0;
        double avarage = 0;
        double countRaters = 0;

        for (Rater rater : this.myRaters) {
            for (int i = 0; i < rater.numRatings(); i++) {
                // get itemlist
                ArrayList<String> list = rater.getItemsRated();
                // movie_id == id 
                if (list.get(i).equals(id)) {
                    double tempRatingNum = rater.getRating(list.get(i));
                    totalRating += tempRatingNum;
                    countRaters +=1;
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
        ArrayList<Rating> ratingsList = new ArrayList<Rating>();
        for (Movie movie : myMovies) {
            double avaRatingNum = getAverageById(movie.getID(), minimalRaters);
            // greater than 0.0 is okey
            if (avaRatingNum != 0.0) {
                Rating rating = new Rating(movie.getTitle(), avaRatingNum);
                ratingsList.add(rating);
            }
        }
        return ratingsList;
    }

    // This method returns the title of the movie with that ID. If the movie ID does not exist, then this method should return a String indicating the ID was not found.
    public String getTitle(String id) {
        for (Movie movie : myMovies) {
            if (movie.getID().equals(id)) {
                return movie.getTitle();
            }
        }
        return id + "was not found";
    }
    
    //This method returns the movie ID of this movie. If the title is not found, return an appropriate message such as “NO SUCH TITLE.”
    public String getID(String title) {
        for (Movie movie : myMovies) {
            if (movie.getTitle().equals(title)) {
                return movie.getID();
            }
        }
        return "NO SUCH TITLE";
    }
}