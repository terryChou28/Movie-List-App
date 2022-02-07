package model;

import java.util.ArrayList;
import java.util.List;

public class MovieList {
    private List<Movie> movieList;

    // EFFECTS: constructs an empty list of movies
    public MovieList() {
        movieList = new ArrayList<>();
    }

    public List<Movie> getMovieList() {
        return movieList;
    }

    public int getNumMovies() {
        return movieList.size();
    }

    public boolean contains(Movie movie) {
        return movieList.contains(movie);
    }

    // MODIFIES: this
    // EFFECTS: adds movie to the list of movies and returns true,
    //          unless it's already in the list then returns false
    public boolean addMovie(Movie movie) {
        if (!movieList.contains(movie)) {
            movieList.add(movie);
            return true;
        }
        return false;
    }

    // MODIFIES: this
    // EFFECTS: removes movie from the list of movies and returns true,
    //          unless it's not in the list then returns false
    public boolean removeMovie(Movie movie) {
        if (movieList.contains(movie)) {
            movieList.remove(movie);
            return true;
        }
        return false;
    }

    // REQUIRES: movieList cannot be empty
    // EFFECTS: returns the movie with the highest approval rating from the list
    public Movie highestRatingMovie(List<Movie> movieList) {
        Movie highest = movieList.get(0);

        for (Movie m : movieList) {
            if (m.getApprovalRating() > highest.getApprovalRating()) {
                highest = m;
            }
        }
        return highest;
    }
}
