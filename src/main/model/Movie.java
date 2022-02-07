package model;

// Represents a movie having a title, box office (in million dollars),
// approval rating, and a user rating
public class Movie {
    private String title;
    private int boxOffice;
    private int approvalRating;
    private int rating;

    // EFFECTS: constructs a movie with the given title, box office (in million $),
    //          and approval rating, also a user rating of 0
    public Movie(String title, int boxOffice, int approvalRating) {
        this.title = title;
        this.boxOffice = boxOffice;
        this.approvalRating = approvalRating;
    }

    public String getTitle() {
        return title;
    }

    public int getBoxOffice() {
        return boxOffice;
    }

    public int getApprovalRating() {
        return approvalRating;
    }

    public int getRating() {
        return rating;
    }

    // MODIFIES: this
    // EFFECTS: rates the movie on a 1 to 5 scale
    public String rateMovie(int rating) {
        this.rating = rating;

        if (!(rating == 3)) {
            if (rating < 3) {
                return "Negative Review";
            } else {
                return "Positive Review";
            }
        } else {
            return "Neutral Review";
        }
    }
}
