package persistence;

import model.Movie;
import model.MovieList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            MovieList ml = new MovieList();
            JsonWriter writer = new JsonWriter("./data/my\0illegal.fileName.json");
            writer.open();
            fail("IOException should have been thrown!");
        } catch (IOException e) {
            // expected
        }
    }

    @Test
    void testWriterEmptyMovieList() {
        try {
            MovieList ml = new MovieList();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyMovieList.json");
            writer.open();
            writer.write(ml);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyMovieList.json");
            ml = reader.read();
            assertEquals(0, ml.getNumMovies());
        } catch (IOException e) {
            fail("Exception should not have been thrown!");
        }
    }

    @Test
    void testWriterGeneralMovieList() {
        try {
            MovieList ml = new MovieList();
            ml.addMovie(new Movie("Spider-Man: No Way Home", 1744, 93));
            ml.addMovie(new Movie("Avengers: Endgame", 2798, 94));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralMovieList.json");
            writer.open();
            writer.write(ml);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralMovieList.json");
            ml = reader.read();
            List<Movie> movies = ml.getMovieList();
            assertEquals(2, movies.size());
            checkMovie("Spider-Man: No Way Home", 1744, 93, movies.get(0));
            checkMovie("Avengers: Endgame", 2798, 94, movies.get(1));
        } catch (IOException e) {
            fail("Exception should not have been thrown!");
        }
    }

}