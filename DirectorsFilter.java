
/**
 * Write a description of DirectorsFilter here.
 * 
 * @author Taiga Shiga
 * @version (a version number or a date)
 */
public class DirectorsFilter implements Filter {

	private String directors;
	private String regex = ",";

	public DirectorsFilter(String directors) {
		this.directors = directors;
	}

	@Override
	public boolean satisfies(String id) {
		// TODO Auto-generated method stub
		boolean isExsitDirector = false;
		String[] splitDirectors = this.directors.split(this.regex);
		for (String d : splitDirectors) {
			if (MovieDatabase.getDirector(id).contains(d)) {
				isExsitDirector = true;
			}
		}
		return isExsitDirector;
	}

}
