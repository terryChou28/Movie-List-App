package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// Represents a movie list
public class MovieList implements Writable {
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

    // REQUIRES: movie must not be null
    // MODIFIES: this
    // EFFECTS: adds movie to the list of movies and returns true,
    //          unless it's already in the list then returns false
    public boolean addMovie(Movie movie) {
        for (Movie m : movieList) {
            if (m.getTitle().equals(movie.getTitle())) {
                return false;
            }
        }
        movieList.add(movie);
        return true;
    }

    // REQUIRES: title has a non-zero length
    // MODIFIES: this
    // EFFECTS: removes movie from the list of movies and returns true,
    //          unless it's not in the list then returns false
    public boolean removeMovie(String title) {
        int index = -1;

        for (Movie m : movieList) {
            if (m.getTitle().equals(title)) {
                index = movieList.indexOf(m);
            }
        }

        if (index == -1) {
            return false;
        }

        Movie movie = movieList.get(index);
        movieList.remove(movie);
        return true;
    }

    // REQUIRES: movieList cannot be empty
    // EFFECTS: returns the movie with the highest approval rating from the list
    public Movie highestRatingMovie(List<Movie> movieList) {
        Movie highest = movieList.get(0);

        for (Movie m : movieList) {
            if (m.getRottenTomatoesRating() > highest.getRottenTomatoesRating()) {
                highest = m;
            }
        }
        return highest;
    }

    // EFFECTS: returns true if the given movie is contained within the list of movies
    public boolean contains(Movie movie) {
        return movieList.contains(movie);
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("movieList", movieToJson());
        return json;
    }

    // EFFECTS: returns movies in this movie list as a JSON array
    private JSONArray movieToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Movie m : movieList) {
            jsonArray.put(m.toJson());
        }

        return jsonArray;
    }
}
