package ui;

import model.Movie;
import model.MovieList;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

// The Teller application is used as a reference for this project
// https://github.students.cs.ubc.ca/CPSC210/TellerApp
// Movie list application
public class MovieListApp {
    private static final String JSON_STORE = "./data/myFile.json";
    private static final String JSON_STORE2 = "./data/myFile2.json";
    private MovieList toWatchList;
    private MovieList watchedList;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonWriter jsonWriter2;
    private JsonReader jsonReader;
    private JsonReader jsonReader2;

    // EFFECTS: runs the movie list application
    public MovieListApp() throws FileNotFoundException {
        runMovieListApp();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runMovieListApp() {
        boolean goOn = true;
        String option;

        setUp();

        System.out.println("\nWelcome to the Movie List Application!");

        while (goOn) {
            displayMenu();
            option = input.next();

            if (option.equals("q")) {
                goOn = false;
            } else {
                processOption(option);
            }
        }

        System.out.println("\nThank you for using the application!");
    }

    // EFFECTS: displays menu of options to user
    public void displayMenu() {
        System.out.println("\nPlease select from:");
        System.out.println("\ta -> add movie");
        System.out.println("\tr -> remove movie");
        System.out.println("\th -> highest rating movie");
        System.out.println("\tt -> rate movie");
        System.out.println("\tv -> to view the list");
        System.out.println("\ts -> save movie list to file");
        System.out.println("\tl -> load movie list from file");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: processes user option
    private void processOption(String option) {
        if (option.equals("a")) {
            toAdd();
        } else if (option.equals("r")) {
            toRemove();
        } else if (option.equals("h")) {
            highestRating();
        } else if (option.equals("t")) {
            toRate();
        } else if (option.equals("v")) {
            toView();
        } else if (option.equals("s")) {
            saveMovieList();
        } else if (option.equals("l")) {
            loadMovieList();
        } else {
            System.out.println("Option not valid!");
        }
    }

    // MODIFIES: this
    // EFFECTS: sets up movie lists
    private void setUp() {
        toWatchList = new MovieList();
        watchedList = new MovieList();
        input = new Scanner(System.in);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonWriter2 = new JsonWriter(JSON_STORE2);
        jsonReader = new JsonReader(JSON_STORE);
        jsonReader2 = new JsonReader(JSON_STORE2);
    }

    // MODIFIES: this
    // EFFECTS: adds a movie to the chosen movie list
    private void toAdd() {
        MovieList movies = chooseList();
        System.out.println("Please enter movie title to add: ");
        input.nextLine();
        String title = input.nextLine();
        System.out.println("Please enter the box office of the movie: ");
        int boxOffice = input.nextInt();
        System.out.println("Please enter Rotten Tomatoes' approval rating for the movie: ");
        int rottenTomatoesRating = input.nextInt();

        Movie movie = new Movie(title, boxOffice, rottenTomatoesRating);

        if (movies.addMovie(movie)) {
            System.out.println("The movie has been added");
        } else {
            System.out.println("The move is already on the list");
        }
    }

    // MODIFIES: this
    // EFFECTS: removes a movie from the chosen movie list
    private void toRemove() {
        MovieList myList = chooseList();
        List<Movie> movies = myList.getMovieList();

        if (movies.isEmpty()) {
            System.out.println("Empty list");
            return;
        }

        System.out.println("Please enter movie title to remove: ");
        input.nextLine();
        String title = input.nextLine();

        if (myList.removeMovie(title)) {
            System.out.println("The movie has been removed");
        } else {
            System.out.println("Movie not on the list");
        }
    }

    // EFFECTS: views movies on the chosen movie list
    private void toView() {
        MovieList myList = chooseList();
        List<Movie> movies = myList.getMovieList();

        if (movies.isEmpty()) {
            System.out.println("Empty list");
            return;
        }

        for (Movie m : movies) {
            System.out.println(m.toString());
        }
    }

    // EFFECTS: tells the user which movie has the highest approval rating on the chosen list
    private void highestRating() {
        MovieList movies = chooseList();
        List<Movie> myList = movies.getMovieList();

        if (myList.isEmpty()) {
            System.out.println("Empty list");
            return;
        }

        Movie highest;
        highest = movies.highestRatingMovie(movies.getMovieList());

        System.out.println("The movie with the highest approval rating is: " + highest.getTitle());
    }

    // MODIFIES: this
    // EFFECTS: rates the movie on a scale of 1 to 5
    private void toRate() {
        MovieList myList = chooseList();
        List<Movie> movies = myList.getMovieList();

        if (movies.isEmpty()) {
            System.out.println("Empty list");
            return;
        }

        System.out.println("Which movie do you want to rate: ");
        input.nextLine();
        String title = input.nextLine();
        int index;
        index = getMovieIndex(movies, title);

        if (index == -1) {
            System.out.println("Movie not on the list");
        } else {
            Movie movie = movies.get(index);
            System.out.println("What is your rating on a 1 to 5 scale: ");
            int rate = input.nextInt();
            System.out.println("Your review is: " + movie.rateMovie(rate));
        }
    }

    // EFFECTS: prompts user to choose between a to-watch movie list
    //          or an already watched movie list and returns it
    private MovieList chooseList() {
        String choice;

        do {
            System.out.println("w for a to-watch list");
            System.out.println("d for watched list");
            choice = input.next();
        } while (!(choice.equals("w") || choice.equals("d")));

        if (choice.equals("w")) {
            return toWatchList;
        } else {
            return watchedList;
        }
    }

    // REQUIRES: movies must not be empty, title has non-zero length
    // MODIFIES: index
    // EFFECTS: returns the index of the movie matching the given title on the movie list,
    //          if not on the list returns -1 as the index
    private int getMovieIndex(List<Movie> movies, String title) {
        int index = -1;

        for (Movie m : movies) {
            if (m.getTitle().equals(title)) {
                index = movies.indexOf(m);
                return index;
            }
        }
        return index;
    }

    // EFFECTS: saves the movie list to file
    private void saveMovieList() {
        try {
            jsonWriter.open();
            jsonWriter2.open();
            jsonWriter.write(toWatchList);
            jsonWriter2.write(watchedList);
            jsonWriter.close();
            jsonWriter2.close();
            System.out.println("To-watch list saved to " + JSON_STORE);
            System.out.println("Watched list saved to " + JSON_STORE2);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads movie list from file
    private void loadMovieList() {
        try {
            toWatchList = jsonReader.read();
            System.out.println("Loaded to-watch list from " + JSON_STORE);
            watchedList = jsonReader2.read();
            System.out.println("Loaded watched list from " + JSON_STORE2);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}
