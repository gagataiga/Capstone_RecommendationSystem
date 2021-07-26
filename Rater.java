
/**
 * Write a description of Rater here.
 * 
 * @author Taiga Shiga 
 * @version (a version number or a date)
 */

import java.util.*;

public interface Rater {

	public void addRating(String id, double rating);

	public HashMap<String, Rating> getMyRating();

	public boolean hasRating(String item);

	public String getID();

	public double getRating(String item);

	public int numRatings();

	public ArrayList<String> getItemsRated();
	

}
