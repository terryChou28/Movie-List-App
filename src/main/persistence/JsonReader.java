package persistence;

import model.Movie;
import model.MovieList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// The JsonSerializationDemo app is used as a reference for this project
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads a movie list from file and returns it;
    //          throws IOException if an error occurs reading data from file
    public MovieList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseMovieList(jsonObject);
    }

    // EFFECTS: reads source file as String and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach((s -> contentBuilder.append(s)));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses movie list from JSON object and returns it
    private MovieList parseMovieList(JSONObject jsonObject) {
        MovieList ml = new MovieList();
        addMovies(ml, jsonObject);
        return ml;
    }

    // MODIFIES: ml
    // EFFECTS: parses movies from JSON object and adds them to movie list
    private void addMovies(MovieList ml, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("movieList");
        for (Object json : jsonArray) {
            JSONObject nextMovie = (JSONObject) json;
            addMovie(ml, nextMovie);
        }
    }

    // MODIFIES: ml
    // EFFECTS: parses a movie from JSON object and adds it to movie list
    private void addMovie(MovieList ml, JSONObject jsonObject) {
        String title = jsonObject.getString("title");
        int boxOffice = jsonObject.getInt("boxOffice");
        int rottenTomatoesRating = jsonObject.getInt("rottenTomatoesRating");
        int rating = jsonObject.getInt("rating");
        Movie movie = new Movie(title, boxOffice, rottenTomatoesRating);
        ml.addMovie(movie);
    }
}
