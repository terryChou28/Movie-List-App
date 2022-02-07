package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MovieTest {
    private Movie myMovie;

    @BeforeEach
    public void runBefore() {
        myMovie = new Movie("Spider-Man: No Way Home", 1744, 93);
    }

    @Test
    public void testConstructor() {
        assertEquals("Spider-Man: No Way Home", myMovie.getTitle());
        assertEquals(1744, myMovie.getBoxOffice());
        assertEquals(93, myMovie.getApprovalRating());
    }

    @Test
    public void testRateMovie() {
        myMovie.rateMovie(5);
        assertEquals(5, myMovie.getRating());
    }
}