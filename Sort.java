import jdk.nashorn.internal.runtime.arrays.ArrayLikeIterator;

/**
 * Write a description of Sort here.
 * 
 * @author Taiga Shgia;
 * @version (a version number or a date)
 */

import java.util.ArrayList;
import java.util.Collections;

public class Sort {
    private ArrayList<Rating> ratingsList;
    
    public Sort(ArrayList<Rating> ratingsList) {
        this.ratingsList = ratingsList;
    }

    public ArrayList<Rating> getRatingList() {
        return ratingsList;
    }
    
    // initialize 

    // 昇順
    public void ascendingOrder() {
        // bubblie sort
        for (int i = 0; i < this.ratingsList.size() - 1; i++) {
            for (int j = 0; j < this.ratingsList.size() - 1; j++) {
                Double value = this.ratingsList.get(j).getValue();
                Double valuePuls = this.ratingsList.get(j + 1).getValue();
                // 現在のvalueと次のvalueを比べてvalueがでかい場合
                if (value > valuePuls) {
                    Collections.swap(ratingsList, j, j + 1);
                }
            }
        }
    }
    

}
