
/**
 * Write a description of MinutesFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MinutesFilter implements Filter{

	private int minMinutes;
	private int maxMinutes;

	public MinutesFilter(int minMinutes, int maxMinutes) {
		this.minMinutes = minMinutes;
		this.maxMinutes = maxMinutes;
	}

	@Override
	public boolean satisfies(String id) {
		// TODO Auto-generated method stub
		return minMinutes <= MovieDatabase.getMinutes(id) && maxMinutes >= MovieDatabase.getMinutes(id);
	}

}
