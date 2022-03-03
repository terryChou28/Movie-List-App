package persistence;

import model.Movie;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkMovie(String title, int boxOffice, int rottenTomatoesRating, Movie movie) {
        assertEquals(title, movie.getTitle());
        assertEquals(boxOffice, movie.getBoxOffice());
        assertEquals(rottenTomatoesRating, movie.getRottenTomatoesRating());
    }
}
