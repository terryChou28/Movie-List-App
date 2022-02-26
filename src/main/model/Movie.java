package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents a movie having a title, box office (in $millions),
// Rotten Tomatoes' approval rating, and a user rating
public class Movie implements Writable {
    private String title;
    private int boxOffice;
    private int rottenTomatoesRating;
    private int rating;

    // REQUIRES: title has a non-zero length, boxOffice and rottenTomatoesRating >= 0
    // EFFECTS: constructs a movie with the given title, box office (in $millions),
    //          and Rotten Tomatoes' approval rating, also a user rating of 0
    public Movie(String title, int boxOffice, int rottenTomatoesRating) {
        this.title = title;
        this.boxOffice = boxOffice;
        this.rottenTomatoesRating = rottenTomatoesRating;
        rating = 0;
    }

    public String getTitle() {
        return title;
    }

    public int getBoxOffice() {
        return boxOffice;
    }

    public int getRottenTomatoesRating() {
        return rottenTomatoesRating;
    }

    public int getRating() {
        return rating;
    }

    // MODIFIES: this
    // EFFECTS: rates the movie on a scale of one to five, ratings of 1 and 2
    //          returns "Negative Review", 3 returns "Neutral Review", and
    //          4 and 5 returns "Positive Review"
    public String rateMovie(int rating) {
        this.rating = rating;

        if (this.rating == 3) {
            return "Neutral Review";
        } else if (this.rating < 3 && this.rating >= 0) {
            return "Negative Review";
        } else if (this.rating > 3 && this.rating <= 5) {
            return "Positive Review";
        } else {
            return "Not in a scale of 1 to 5";
        }
    }

    // EFFECTS: returns a string representation of a movie
    public String toString() {
        return "Movie Title: " + title + ", Box Office: " + boxOffice + ", Approval Rating: "
                + rottenTomatoesRating + ", User Rating: " + rating;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("title", title);
        json.put("boxOffice", boxOffice);
        json.put("rottenTomatoesRating", rottenTomatoesRating);
        json.put("rating", rating);
        return json;
    }
}
