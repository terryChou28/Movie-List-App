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
}
