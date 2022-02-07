package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MovieListTest {
    private MovieList myMovieList;
    private Movie myMovie;
    private Movie myMovie2;

    @BeforeEach
    public void runBefore() {
        myMovieList = new MovieList();
        myMovie = new Movie("Spider-Man: No Way Home", 1744, 93);
        myMovie2 = new Movie("Avengers: Endgame", 2798, 94);
    }

    @Test
    public void testConstructor() {
        assertEquals(0, myMovieList.getNumMovies());
    }

    @Test
    public void testAddMovieSuccess() {
        assertTrue(myMovieList.addMovie(myMovie));
        assertEquals(1, myMovieList.getNumMovies());
        assertTrue(myMovieList.contains(myMovie));

        assertTrue(myMovieList.addMovie(myMovie2));
        assertEquals(2, myMovieList.getNumMovies());
        assertTrue(myMovieList.contains(myMovie2));
    }

    @Test
    public void testAddMovieFailure() {
        assertTrue(myMovieList.addMovie(myMovie));
        assertEquals(1, myMovieList.getNumMovies());
        assertTrue(myMovieList.contains(myMovie));

        assertFalse(myMovieList.addMovie(myMovie));
    }

    @Test
    public void testRemoveMovieSuccess() {
        myMovieList.addMovie(myMovie);
        myMovieList.addMovie(myMovie2);
        assertEquals(2, myMovieList.getNumMovies());
        assertTrue(myMovieList.removeMovie(myMovie));
        assertEquals(1, myMovieList.getNumMovies());
        assertFalse(myMovieList.contains(myMovie));

        assertTrue(myMovieList.removeMovie(myMovie2));
        assertEquals(0, myMovieList.getNumMovies());
        assertFalse(myMovieList.contains(myMovie2));
    }

    @Test
    public void testRemoveMovieFailure() {
        assertFalse(myMovieList.removeMovie(myMovie));
    }

    @Test
    public void testHighestRatingMovie() {
        myMovieList.addMovie(myMovie);
        myMovieList.addMovie(myMovie2);
        assertEquals(myMovie2, myMovieList.highestRatingMovie(myMovieList.getMovieList()));
    }

}