
/**
 * Write a description of EfficientRater here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class EfficientRater implements Rater{
    private String myID;
    private HashMap<String,Rating> myRatings;

    public EfficientRater(String id) {
        myID = id;
        myRatings = new HashMap<String, Rating>();
    }

    public void addRating(String item, double rating) {
        Rating newRating = new Rating(item, rating);
        // itemn = movie id
        // newRating = rating of particular movie
        myRatings.put(item, newRating);
    }

    public boolean hasRating(String item) {
        if(myRatings.containsKey(item)){
            return true;
        }
        return false;
    }

    public String getID() {
        return myID;
    }

    public HashMap<String, Rating> getMyRating() {
        return this.myRatings;
    }
    
    
    public double getRating(String item) {
        if (myRatings.get(item) != null) {
            return myRatings.get(item).getValue();
        }
        return -1;
    }

    public int numRatings() {
        return myRatings.size();
    }

    public ArrayList<String> getItemsRated() {
        if(myRatings == null){
            ArrayList<String> nullList = new ArrayList<String>();
            nullList.add("null");
            return nullList;
        }
        ArrayList<String> list = new ArrayList<String>(myRatings.keySet());
        return list;
    }
    
}
