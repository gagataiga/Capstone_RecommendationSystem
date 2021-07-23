
/**
 * Write a description of FirstRatings here.
 * 
 * @author Taiga shiga
 * 
 */

import edu.duke.*;
import java.lang.reflect.Array;
import java.util.*;

import javax.lang.model.element.Element;

import org.apache.commons.csv.*;

public class FirstRatings {

    public ArrayList<Movie> loadMovies(String filename) {
        ArrayList<Movie> movieList = new ArrayList<Movie>();
        FileResource fr = new FileResource("data/" + filename);
        CSVParser parser = fr.getCSVParser();

        for (CSVRecord record : parser) {
            Movie movie = new Movie(record.get("id"), record.get("title"), record.get("year"), record.get("genre"),
                    record.get("director"), record.get("country"), record.get("poster"),
                    Integer.parseInt(record.get("minutes")));
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
        ArrayList<Movie> movielist = loadMovies("ratedmoviesfull.csv");
        // conform the number of movielist
        // ratedmoviesfull.csv -> 3142 ok   
         //for (Movie movie : movielist) {
        //  System.out.println(movie.getDirector());
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
        System.out.println("count Comedies " + countComedies);
        System.out.println("greater than 150 minutes " +gt150minMovies);

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

        System.out.println("the maximum number of movies by any director " + maximum);
        System.out.println("who the directors are that directed that many movies ");
        for (String s : maxdirectores){
            System.out.println(s);
        }
    }

        public int isIdExsit(ArrayList<Rater> raterList, String rateID) {
            for (int i = 0; i < raterList.size(); i++) {
            if (raterList.get(i).getID().equals(rateID)) {
                return i;
            }
        }
        return -1;
    }

    public ArrayList<Rater> loadRaters(String fileName) {
        FileResource fr = new FileResource("data/" + fileName);
        CSVParser parser = fr.getCSVParser();
    
        ArrayList<Rater> raterList = new ArrayList<Rater>();
        for (CSVRecord rate : parser) {
            
            // |rater_id|movie_id|rating|time
            String id = rate.get("rater_id");
            String item = rate.get("movie_id");
            Double value = Double.parseDouble(rate.get("rating"));

            int idExist = isIdExsit(raterList, id);
            // raterlist doen't contain id
            if (idExist == -1) {
                Rater currentRater = new EfficientRater(id);
                currentRater.getRating(item);
                // add currenRater to raterList;
                raterList.add(currentRater);
                // raterlist contains id
            } else {
                raterList.get(idExist).addRating(item, value);
            }
        }
        return raterList;
    }

    // course week 3 
    public ArrayList<Rater> loadRaters2(String filename) {
        ArrayList<Rater> loadRaters = new ArrayList<Rater>();
        FileResource fr = new FileResource("data/" + filename);
        CSVParser parser = fr.getCSVParser();

        for (CSVRecord record : parser) {
            String raterID = record.get("rater_id");
            String movieID = record.get("movie_id");
            Double raptingValue = Double.parseDouble(record.get("rating"));

            Rater rater = new EfficientRater(raterID);
            loadRaters.add(rater);
            rater.addRating(movieID, raptingValue);
        }
        return loadRaters;
    }


    public HashMap<String, HashMap<String, Rating>> fromCsvRaters(CSVParser parser) {
        HashMap<String, HashMap<String, Rating>> ratingMap = new HashMap<String, HashMap<String, Rating>>();

        for (CSVRecord record : parser) {
            // record is like this
            // rater_id  movie_id	rating	time
            // 1	68646	10	1381620027
            // 1	113277	10	1379466669
            // 2	1798709	10	1389948338
            // 2	790636	7	1389963947

            String raterID = record.get("rater_id");
            String movieID = record.get("movie_id");
            Double raptingValue = Double.parseDouble(record.get("rating"));
            // get type Rater 
            Rater currentRater = new EfficientRater(raterID);
            currentRater.addRating(movieID, raptingValue);
              if (!ratingMap.containsKey(currentRater.getID())) {
                 ratingMap.put(raterID, currentRater.getMyRating());
             } else {
                 // case 0068646
                // 006846=[0068646, 10.0]
                ratingMap.get(currentRater.getID()).put(movieID,currentRater.getMyRating().get(movieID));
            }
        }
        return ratingMap;
    }
    

    public HashMap<String, HashMap<String, Rating>> loadRatings(String filename) {
        FileResource fr = new FileResource("data/" + filename);
        CSVParser parser = fr.getCSVParser();
        HashMap<String, HashMap<String, Rating>> ratingMapList = fromCsvRaters(parser);
    
        return ratingMapList;
    }

    public ArrayList<String> countMaxRater(ArrayList<Rater> raterList, Double max) {
        ArrayList<String> maxRaters = new ArrayList<String>();
        for (Rater rater : raterList) {
            ArrayList<String> item = rater.getItemsRated();
            for (int i = 0; i < rater.numRatings(); i++) {

                if (max == rater.getRating(item.get(i)) && !maxRaters.contains(rater.getID())) {
                    maxRaters.add(rater.getID());
                }
            }
        }
        return maxRaters;
    }

    public void testLoadRaters(){
        ArrayList<Rater> raterList = loadRaters("ratings.csv");
        //the maximum number of ratings by any rater. 
        Double maximum = 0.0;
        for (Rater rater : raterList) {
            // items
            ArrayList<String> item = rater.getItemsRated();
            for (int i = 0; i < rater.numRatings(); i++) {
                // 
                Double tempmax = rater.getRating(item.get(i));
                if (maximum == 0) {
                    maximum = tempmax;
                } else if (maximum < tempmax) {
                    maximum = tempmax;
                }
            }
        }

        // Add code to find the number of ratings a particular movie has. If you run this code on the file ratings_short.csv for the movie “1798709”, you will see it was rated by four raters.

        String spcificMovieId = "1798709";
        int speCount = 0;
        for (Rater rater : raterList) {
            ArrayList<String> item = rater.getItemsRated();
            for (int k = 0; k < rater.numRatings(); k++) {
                if (item.get(k).equals(spcificMovieId)) {
                    speCount += 1;
                }
            }
        }

        System.out.println(" the number of ratings a particular movie is " + speCount);

        // how many raters have this maximum number of ratings and who those raters are. 
        ArrayList<String> maxRaters = countMaxRater(raterList, maximum);
        System.out.println("max raters list size " + maxRaters.size());
        for (String id : maxRaters) {
            System.out.println("how many raters have this maximum number of ratings and who those raters are : " + id);
        }

        //how many different movies have been rated by all these raters.
        ArrayList<String> differentMovieList = new ArrayList<String>();
        for (Rater rater : raterList) {
        
            ArrayList<String> list = rater.getItemsRated();
            for (int i = 0; i < rater.numRatings(); i++) {
                if(!differentMovieList.contains(list.get(i))){
                    differentMovieList.add(list.get(i));
                }
            }
        }
    
        System.out.println("total diffrent movie " + differentMovieList.size());
    }
}
