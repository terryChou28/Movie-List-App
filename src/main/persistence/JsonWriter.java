package persistence;

import model.Movie;
import model.MovieList;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

// The JsonSerializationDemo app is used as a reference for this project
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
// Represents a writer that writes JSON representation of a movie list to file
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs a writer to write to the destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    //          be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter((destination));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of a movie list to file
    public void write(MovieList ml) {
        JSONObject json = ml.toJson();
        saveToFile(json.toString(TAB));
    }

//    public void write2(JList<String> jl) {
//        public JSONObject tojson(JList jl {
//            JSONArray jsonArray = new JSONArray();
//
//            for (Movie m : jl) {
//                jsonArray.put(m.toJson());
//            }
//
//            return jsonArray;
//        }
//        }
//        JSONObject json = jl.toJson();
//        saveToFile(json.toString(TAB));
//    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }
}
