import java.util.ArrayList;

/**
 * Write a description of Recommender here.
 * 
 * @author Taiga Shiga
 * @version (a version number or a date)
 */

public interface Recommender {
    
    // It returns a list of strings representing movie IDs that will be used to present movies to the user for them to rate
    public ArrayList<String> getItemsToRate();

    
    public void printRecommendationsFor(String webRaterID);
}
