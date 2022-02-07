package model;

public class Movie {
    private int id;
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

    public void rateMovie(int rating) {
        this.rating = rating;
    }
}
