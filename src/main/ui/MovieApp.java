package ui;

import model.Movie;
import model.MovieList;

import java.util.List;
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
        String option;
        input = new Scanner(System.in);
        wantToWatchMovies = new MovieList();
        alreadyWatchedMovies = new MovieList();

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
    }

    public void displayMenu() {
        System.out.println("\nPlease select from:");
        System.out.println("\ta -> add movie");
        System.out.println("\tr -> remove movie");
        System.out.println("\th -> highest rating movie");
        System.out.println("\tt -> rate movie");
    }

    private void processOption(String option) {
        switch (option) {
            case "a":
                toAdd();
                break;
            case "r":
                toRemove();
                break;
            case "h":
                highestRating();
                break;
            case "t":
                toRate();
                break;
            default:
                System.out.println("Option not valid!");
                break;
        }
    }

    private MovieList chooseList() {
        String choice;

        do {
            System.out.println("w for want to watch movies");
            System.out.println("a for already watched movies");
            choice = input.next();
        } while (!(choice.equals("w") || choice.equals("a")));

        if (choice.equals("w")) {
            return wantToWatchMovies;
        } else {
            return alreadyWatchedMovies;
        }
    }

    private void toAdd() {
        MovieList movies = chooseList();
        System.out.println("Please enter movie title to add: ");
        String title = input.next();
        System.out.println("Please enter the box office of the movie");
        int boxOffice = input.nextInt();
        System.out.println("Please enter the approval rating of the movie");
        int approvalRating = input.nextInt();

        Movie movie = new Movie(title, boxOffice, approvalRating);

        if (movies.addMovie(movie)) {
            System.out.println("The movie has been added");
        } else {
            System.out.println("The move is already on the list");
        }
    }

    private void toRemove() {
        MovieList myList = chooseList();
        List<Movie> movies = myList.getMovieList();

        if (movies.isEmpty()) {
            System.out.println("Wrong list");
            return;
        }

        System.out.println("Please enter movie title to remove: ");
        String title = input.next();

        int index = 0;
        for (Movie m : movies) {
            if (m.getTitle().equals(title)) {
                index = movies.indexOf(m);
            }
        }
        Movie movie = movies.get(index);

        if (myList.removeMovie(movie)) {
            System.out.println("The movie has been removed");
        } else {
            System.out.println("Movie not on the list");
        }
    }

    private void highestRating() {
        MovieList movies = chooseList();
        List<Movie> myList = movies.getMovieList();

        if (myList.isEmpty()) {
            System.out.println("Wrong list");
            return;
        }

        Movie highest;
        highest = movies.highestRatingMovie(movies.getMovieList());

        System.out.println("The movie with the highest approval rating is: " + highest.getTitle());
    }

    private void toRate() {
        MovieList myList = chooseList();
        List<Movie> movies = myList.getMovieList();

        if (movies.isEmpty()) {
            System.out.println("Wrong list");
            return;
        }

        System.out.println("Which movie do you want to rate: ");

        String title = input.next();
        int index = 0;

        for (Movie m : movies) {
            if (m.getTitle().equals(title)) {
                index = movies.indexOf(m);
            }
        }

        Movie movie = movies.get(index);
        System.out.println("What is your rating on a 1 to 5 scale: ");
        int rate = input.nextInt();
        System.out.println("Your review is: " + movie.rateMovie(rate));
    }
}
