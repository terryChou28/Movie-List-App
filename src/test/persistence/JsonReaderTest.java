package persistence;

import model.Movie;
import model.MovieList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            MovieList ml = reader.read();
            fail("IOException should have been thrown!");
        } catch (IOException e) {
            // expected
        }
    }

    @Test
    void testReaderEmptyMovieList() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyMovieList.json");
        try {
            MovieList ml = reader.read();
            assertEquals(0, ml.getNumMovies());
        } catch (IOException e) {
            fail("Cannot read from file");
        }
    }

    @Test
    void testReaderGeneralMovieList() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralMovieList.json");
        try {
            MovieList ml = reader.read();
            List<Movie> movies = ml.getMovieList();
            assertEquals(2, movies.size());
            checkMovie("Spider-Man: No Way Home", 1744, 93, movies.get(0));
            checkMovie("Avengers: Endgame", 2798, 94, movies.get(1));
        } catch (IOException e) {
            fail("Cannot read from file");
        }
    }
}
