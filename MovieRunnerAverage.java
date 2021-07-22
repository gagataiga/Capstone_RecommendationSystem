import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;


/**
 * Write a description of MovieRunnerAverage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MovieRunnerAverage {
		

	public void printAverageRatings() {
		SecondRatings secondRatings = new SecondRatings();
		System.out.println("movie size " + secondRatings.getMovieSize());
		System.out.println("rating size " + secondRatings.getRaterSize());

		ArrayList<Rating> ratingList = secondRatings.getAverageRatings(3);
		// bubblie sort
		for (int i = 0; i < ratingList.size() - 1; i++) {
			for (int j = 0; j < ratingList.size() - 1; j++) {
				Double value = ratingList.get(j).getValue();
				Double valuePuls = ratingList.get(j + 1).getValue();
				// 現在のvalueと次のvalueを比べてvalueがでかい場合
				if (value > valuePuls) {
					Collections.swap(ratingList, j, j + 1);
				}
			}
		}

		for (Rating rating : ratingList) {
			System.out.println("movie: " + rating.getItem() + " rating: "+ rating.getValue() );
		}
	}

}
