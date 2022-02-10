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
        assertEquals("Positive Review", myMovie.rateMovie(5));
        assertEquals(5, myMovie.getRating());
        assertEquals("Positive Review", myMovie.rateMovie(4));
        assertEquals(4, myMovie.getRating());

        assertEquals("Negative Review", myMovie.rateMovie(1));
        assertEquals(1, myMovie.getRating());
        assertEquals("Negative Review", myMovie.rateMovie(0));
        assertEquals(0, myMovie.getRating());

        assertEquals("Neutral Review", myMovie.rateMovie(3));
        assertEquals(3, myMovie.getRating());

        assertEquals("Not in a scale of 1 to 5", myMovie.rateMovie(6));
        assertEquals("Not in a scale of 1 to 5", myMovie.rateMovie(-1));
    }

    @Test
    public void testToString() {
        assertTrue(myMovie.toString().contains("Title: Spider-Man: No Way Home, Box Office: 1744, " +
                "Approval Rating: 93, User Rating: 0"));
    }
}