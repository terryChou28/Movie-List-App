package ui;

import model.Movie;
import model.MovieList;

import java.util.Scanner;

public class MovieApp {
    private MovieList wantToWatchMovies;
    private MovieList alreadyWatchedMovies;
    private Scanner input;

    // EFFECTS: runs the movie application
    public MovieApp() {
        runMovieApp();
    }

    private void runMovieApp() {
        boolean goOn = true;
        String command;
        input = new Scanner(System.in);
        wantToWatchMovies = new MovieList();
        alreadyWatchedMovies = new MovieList();

        while (goOn) {
            displayMenu();
            command = input.next();

            if (command.equals("q")) {
                goOn = false;
            } else {
                processCommand(command);
            }
        }
    }

    public void displayMenu() {
        System.out.println("\nPlease select from:");
        System.out.println("\ta -> add movie");
        System.out.println("\tr -> remove movie");
        System.out.println("\th -> highest rating movie");
        System.out.println("\tt -> rate movie");
    }

    private void processCommand(String command) {
        if (command.equals("a")) {
            doAdd();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    private MovieList selectList() {
        String select = "";

        while (!(select.equals("w") || select.equals("a"))) {
            System.out.println("w for want to watch movies");
            System.out.println("a for already watched movies");
            select = input.next();
        }

        if (select.equals("w")) {
            return wantToWatchMovies;
        } else {
            return alreadyWatchedMovies;
        }
    }

    private void doAdd() {
        MovieList select = selectList();
        System.out.println("Please enter the title of movie to add: ");
        String title = input.next();
        System.out.println("Please enter the box office of the movie");
        int boxOffice = input.nextInt();
        System.out.println("Please enter the approval rating of the movie");
        int approvalRating = input.nextInt();

        Movie movie = new Movie(title, boxOffice, approvalRating);
        select.addMovie(movie);
    }
}
