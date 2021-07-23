
/**
 * Write a description of GenreFilter here.
 * 
 * @author Taiga Shiga
 * @version (a version number or a date)
 */

public class GenreFilter implements Filter {

	private String genre;

	public GenreFilter(String genrerepresenting) {
		this.genre = genrerepresenting;
	}

	@Override
	public boolean satisfies(String id) {
		// TODO Auto-generated method stub
		return MovieDatabase.getGenres(id).contains(genre);
	}

}
